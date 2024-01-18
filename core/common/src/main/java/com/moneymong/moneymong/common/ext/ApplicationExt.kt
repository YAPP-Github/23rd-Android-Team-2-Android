package com.moneymong.moneymong.common.ext

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

fun Context.hasPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun File.toMultipart(): MultipartBody.Part {
    val bitmap = BitmapFactory.decodeFile(this.path)
    val oldExif = ExifInterface(this.path)
    val exifOrientation = oldExif.getAttribute(ExifInterface.TAG_ORIENTATION)

    bitmap.compress(Bitmap.CompressFormat.JPEG, 25, FileOutputStream(this))
    if (exifOrientation != null) {
        val newExif = ExifInterface(this.path)
        newExif.setAttribute(ExifInterface.TAG_ORIENTATION, exifOrientation)
        newExif.saveAttributes()
    }

    val requestBody = this.asRequestBody("image/jpeg".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name = "file", this.name, requestBody)
}

fun String.base64ToFile(context: Context): File? {
    val bytes = Base64.decode(this, Base64.DEFAULT)
    val file = File(context.externalCacheDir, "temp_${System.currentTimeMillis()}.jpeg")
    var fileOutputStream: FileOutputStream
    try {
        fileOutputStream = FileOutputStream(file)
    } catch (e: FileNotFoundException) {
        return null
    }

    val bufferedOutputStream = BufferedOutputStream(fileOutputStream)
    try {
        bufferedOutputStream.write(bytes)
    } catch (e: IOException) {
        return null
    }

    return file
}

fun Uri.encodingBase64(context: Context): String {
    val fileInputStream = context.contentResolver.openInputStream(this)
    val stream = ByteArrayOutputStream()
    val bitmap = BitmapFactory.decodeStream(fileInputStream)
    val rotatedBitmap = rotateImageIfRequired(
        context = context,
        bitmap = bitmap,
        uri = this
    )

    if (rotatedBitmap != null) {
        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream)
    } else {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream)
    }

    val imageData = stream.toByteArray()

    return Base64.encodeToString(imageData, Base64.NO_WRAP)
}

private fun rotateImageIfRequired(context: Context, bitmap: Bitmap, uri: Uri): Bitmap? {
    val input = context.contentResolver.openInputStream(uri) ?: return null

    val exif = if (Build.VERSION.SDK_INT > 23) {
        ExifInterface(input)
    } else {
        ExifInterface(uri?.path ?: return null)
    }

    val orientation =
        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270)
        else -> bitmap
    }
}

private fun rotateImage(bitmap: Bitmap, degree: Int): Bitmap? {
    val matrix = Matrix()
    matrix.postRotate(degree.toFloat())
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}
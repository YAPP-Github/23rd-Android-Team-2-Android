package com.moneymong.moneymong.ocr.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Base64
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

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

fun String.encodingBase64(context: Context): String {
    val parse = Uri.parse(this)
    val fileInputStream = context.contentResolver.openInputStream(parse)
    val stream = ByteArrayOutputStream()
    val bitmap = BitmapFactory.decodeStream(fileInputStream)
    val rotatedBitmap = rotateImageIfRequired(
        context = context,
        bitmap = bitmap,
        uri = parse
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
        ExifInterface(uri.path!!)
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
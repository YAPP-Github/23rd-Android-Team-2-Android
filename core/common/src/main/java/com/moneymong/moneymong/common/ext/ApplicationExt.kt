package com.moneymong.moneymong.common.ext

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.util.Base64
import androidx.core.content.ContextCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.BufferedOutputStream
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
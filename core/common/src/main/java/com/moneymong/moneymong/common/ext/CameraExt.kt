package com.moneymong.moneymong.common.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
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
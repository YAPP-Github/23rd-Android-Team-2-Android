package com.moneymong.moneymong.network.request.ocr

import okhttp3.MultipartBody
import retrofit2.http.Part

data class FileUploadRequest(
    @Part val file: MultipartBody.Part,
    val dirName: String
)

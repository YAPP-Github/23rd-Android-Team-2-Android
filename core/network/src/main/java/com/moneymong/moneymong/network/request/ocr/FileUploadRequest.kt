package com.moneymong.moneymong.network.request.ocr

import okhttp3.MultipartBody

data class FileUploadRequest(
    val file: MultipartBody.Part,
    val dirName: String
)

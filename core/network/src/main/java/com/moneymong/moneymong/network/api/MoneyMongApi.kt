package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.response.ocr.FileUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MoneyMongApi {

    @Multipart
    @POST("api/v1/images")
    suspend fun postFileUpload(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Part file: MultipartBody.Part
    ): Result<FileUploadResponse>
}
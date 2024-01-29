package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import com.moneymong.moneymong.network.response.ocr.FileUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoneyMongApi {

    @Multipart
    @POST("api/v1/images")
    suspend fun postFileUpload(
        @Part file: MultipartBody.Part
    ): Result<FileUploadResponse>
}
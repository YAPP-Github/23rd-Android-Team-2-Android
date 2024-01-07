package com.moneymong.moneymong.network.api.signup

import com.moneymong.moneymong.network.request.signup.UnivRequest
import com.moneymong.moneymong.network.response.signup.UnivResponse
import com.moneymong.moneymong.network.response.signup.UniversitiesReponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UniversityApi {

    @GET("api/v1/user-university")
    suspend fun userUniv(
    ): Result<UnivResponse>

    @POST("api/v1/user-university")
    suspend fun createUniv(
        @Body body: UnivRequest
    ): Result<Unit>

    @GET("api/v1/universities")
    suspend fun searchUniv(
        @Query("keyword") searchQuery: String
    ): Result<UniversitiesReponse>
}
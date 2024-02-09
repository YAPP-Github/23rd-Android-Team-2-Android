package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.signup.UnivRequest
import com.moneymong.moneymong.network.response.signup.UniversitiesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UniversityApi {

    @POST("api/v1/user-university")
    suspend fun createUniv(
        @Body body: UnivRequest
    ): Result<Unit>

    @GET("api/v1/universities")
    suspend fun searchUniv(
        @Query("keyword") searchQuery: String
    ): Result<UniversitiesResponse>
}
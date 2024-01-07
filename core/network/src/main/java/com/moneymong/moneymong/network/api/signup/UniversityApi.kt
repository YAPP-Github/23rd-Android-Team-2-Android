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
        @Header("Authorization") authToken: String = "Bearer "
    ): Result<UnivResponse>

    @POST("api/v1/user-university")
    suspend fun createUniv(
        @Body body : UnivRequest,
        @Header("Authorization") authToken: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE3MDQ1Mjg3NTQsImV4cCI6MTczNjA4NjM1NH0.MqWNFc-OjcjFCSJuZcp0_sNJtLvqkID3wMKu2vUKZkY"
    ) : Result<Unit>

    @GET("api/v1/universities")
    suspend fun searchUniv(
        @Query("keyword") searchQuery: String,
        @Header("Authorization") authToken: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE3MDQ1Mjg3NTQsImV4cCI6MTczNjA4NjM1NH0.MqWNFc-OjcjFCSJuZcp0_sNJtLvqkID3wMKu2vUKZkY"
    ): Result<UniversitiesReponse>
}
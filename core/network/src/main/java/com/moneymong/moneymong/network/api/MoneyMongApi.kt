package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoneyMongApi {

    @POST("/api/v1/agencies/{agencyId}/invitation-code")
    suspend fun agencyCodeNumbers(
        @Header("Authorization") authToken: String,
        @Path("agencyId") agencyId: Long,
        @Body body: AgencyJoinRequest,
    ): Result<AgencyJoinResponse>

    @GET("api/v1/agencies")
    suspend fun getAgencies(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Result<AgenciesGetResponse>


    @POST("api/v1/agencies")
    suspend fun registerAgency(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Body request: AgencyRegisterRequest,
    ): Result<Unit>
}
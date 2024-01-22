package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MoneyMongApi {

    @POST("/api/v1/agencies/{agencyId}/invitation-code")
    suspend fun agencyCodeNumbers(
        @Header("Authorization") authToken: String,
        @Path("agencyId") agencyId: Long,
        @Body body: String,
    ): Result<AgencyJoinResponse>
}
package com.moneymong.moneymong.network.api.agency

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.response.AgencyJoinResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface AgencyJoinApi {
    @POST("/api/v1/agencies/{agencyId}/invitation-code")
    suspend fun agencyCodeNumbers(
        @Header("Authorization") authToken: String,
        @Path("agencyId") agencyId: Int,
        @Body body: AgencyJoinRequest
    ): Result<AgencyJoinResponse>
}


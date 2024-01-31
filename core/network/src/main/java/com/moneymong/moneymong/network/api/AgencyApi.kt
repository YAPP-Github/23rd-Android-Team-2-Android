package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.agency.AgencyJoinRequest
import com.moneymong.moneymong.network.request.agency.AgencyRegisterRequest
import com.moneymong.moneymong.network.response.agency.AgenciesGetResponse
import com.moneymong.moneymong.network.response.agency.AgencyJoinResponse
import com.moneymong.moneymong.network.response.agency.MyAgencyResponse
import com.moneymong.moneymong.network.response.agency.RegisterAgencyResponse
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AgencyApi {

    // GET
    @GET("api/v1/agencies/{agencyId}/invitation-code")
    suspend fun getInvitationCode(
        @Path("agencyId") agencyId: Long
    ): Result<InvitationCodeResponse>

    @GET("api/v1/agencies")
    suspend fun getAgencies(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Result<AgenciesGetResponse>

    @GET("api/v1/agencies/me")
    suspend fun fetchMyAgencyList(): Result<List<MyAgencyResponse>>

    // POST
    @POST("/api/v1/agencies/{agencyId}/invitation-code")
    suspend fun agencyCodeNumbers(
        @Path("agencyId") agencyId: Long,
        @Body body: AgencyJoinRequest
    ): Result<AgencyJoinResponse>

    @POST("api/v1/agencies")
    suspend fun registerAgency(
        @Body request: AgencyRegisterRequest
    ): Result<RegisterAgencyResponse>

    // PATCH
    @PATCH("api/v1/agencies/{agencyId}/invitation-code")
    suspend fun reInvitationCode(
        @Path("agencyId") agencyId: Long
    ): Result<InvitationCodeResponse>
}
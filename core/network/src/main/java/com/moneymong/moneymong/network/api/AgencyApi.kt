package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AgencyApi {

    @GET("api/v1/agencies/{agencyId}/invitation-code")
    suspend fun getInvitationCode(
        @Path("agencyId") agencyId: Long,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<InvitationCodeResponse>

    @PATCH("api/v1/agencies/{agencyId}/invitation-code")
    suspend fun reInvitationCode(
        @Path("agencyId") agencyId: Long,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<InvitationCodeResponse>
}
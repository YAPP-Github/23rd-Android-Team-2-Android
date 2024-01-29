package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.member.MemberBlockRequest
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.MemberListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MemberApi {

    @GET("api/v1/agencies/{agencyId}/agency-users")
    suspend fun getMemberLists(
        @Path("agencyId") agencyId: Long,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<MemberListResponse>


    @PATCH("api/v1/agencies/{agencyId}/agency-users/roles")
    suspend fun updateMemberAuthor(
        @Path("agencyId") agencyId: Long,
        @Body body: UpdateAuthorRequest,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<Unit>


    @PATCH("api/v1/agencies/{agencyId}/agency-users/roles/block")
    suspend fun blockMemberAuthor(
        @Path("agencyId") agencyId: Long,
        @Body body: MemberBlockRequest,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<Unit>
}
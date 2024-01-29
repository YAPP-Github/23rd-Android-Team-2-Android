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
        @Path("agencyId") agencyId: Long
    ): Result<MemberListResponse>


    @PATCH("api/v1/agencies/{agencyId}/agency-users/roles")
    suspend fun updateMemberAuthor(
        @Path("agencyId") agencyId: Long,
        @Body body: UpdateAuthorRequest
    ): Result<Unit>


    @PATCH("api/v1/agencies/{agencyId}/agency-users/roles/block")
    suspend fun blockMemberAuthor(
        @Path("agencyId") agencyId: Long,
        @Body body: MemberBlockRequest
    ): Result<Unit>
}
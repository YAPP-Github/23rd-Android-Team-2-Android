package com.moneymong.moneymong.network.api

import com.moneymong.moneymong.network.request.member.MemberBlockRequest
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse
import com.moneymong.moneymong.network.response.ocr.FileUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface MoneyMongApi {

    @Multipart
    @POST("api/v1/images")
    suspend fun postFileUpload(
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4",
        @Part file: MultipartBody.Part
    ): Result<FileUploadResponse>

    //멤버 초대코드 조회
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


    //멤버 목록 조회 기능 구현
    @GET("api/v1/agencies/{agencyId}/agency-users")
    suspend fun getMemberLists(
        @Path("agencyId") agencyId: Long,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<MemberListResponse>

    //멤머 내 권한 변경
    @PATCH("api/v1/agencies/{agencyId}/agency-users/roles")
    suspend fun updateMemberAuthor(
        @Path("agencyId") agencyId: Long,
        @Body body: UpdateAuthorRequest,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<Unit>

    //멤버 내보내기 기능
    @PATCH("api/v1/agencies/{agencyId}/agency-users/roles/block")
    suspend fun blockMemberAuthor(
        @Path("agencyId") agencyId: Long,
        @Body body: MemberBlockRequest,
        @Header("Authorization") header: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjozLCJpYXQiOjE3MDQ3MTU0NTEsImV4cCI6MTczNjI3MzA1MX0.2yYEy71Gz4YIz0DYzlx0glYMgZA0JAZs05jsVRvvQx4"
    ): Result<Unit>


}
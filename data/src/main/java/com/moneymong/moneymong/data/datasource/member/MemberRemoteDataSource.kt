package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam
import com.moneymong.moneymong.network.request.member.MemberBlockRequest
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse

interface MemberRemoteDataSource {
    suspend fun getInvitationCode(agencyId: Long): Result<InvitationCodeResponse>
    suspend fun reInvitationCode(agencyId: Long): Result<InvitationCodeResponse>
    suspend fun getMemberLists(agencyId: Long): Result<MemberListResponse>
    suspend fun updateMemberAuthor(agencyId : Long, data : UpdateAuthorRequest) : Result<Unit>
    suspend fun blockMemberAuthor(agencyId: Long, data : MemberBlockRequest) : Result<Unit>
}
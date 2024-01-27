package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse

interface MemberRemoteDataSource {
    suspend fun getInvitationCode(data: Long): Result<InvitationCodeResponse>
    suspend fun reInvitationCode(data: Long): Result<InvitationCodeResponse>
    suspend fun memberList(data: Long) : Result<MemberListResponse>
    suspend fun updateMemberAuthor(agencyId : Long, data : UpdateAuthorRequest) : Result<Unit>
}
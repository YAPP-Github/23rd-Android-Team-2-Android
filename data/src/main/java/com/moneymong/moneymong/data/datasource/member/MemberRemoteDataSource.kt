package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse

interface MemberRemoteDataSource {
    suspend fun getInvitationCode(agencyId: Long): Result<InvitationCodeResponse>
    suspend fun reInvitationCode(agencyId: Long): Result<InvitationCodeResponse>
    suspend fun getMemberLists(agencyId: Long): Result<MemberListResponse>
}
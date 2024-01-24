package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.network.response.member.InvitationCodeResponse

interface MemberRemoteDataSource {
    suspend fun getInvitationCode(data: Long): Result<InvitationCodeResponse>
    suspend fun reInvitationCode(data: Long): Result<InvitationCodeResponse>

}
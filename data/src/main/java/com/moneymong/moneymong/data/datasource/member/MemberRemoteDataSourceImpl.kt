package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import javax.inject.Inject

class MemberRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
) : MemberRemoteDataSource {
    override suspend fun getInvitationCode(data: Long): Result<InvitationCodeResponse> {
        return moneyMongApi.getInvitationCode(data)
    }

    override suspend fun reInvitationCode(data: Long): Result<InvitationCodeResponse> {
        return moneyMongApi.reInvitationCode(data)
    }
}
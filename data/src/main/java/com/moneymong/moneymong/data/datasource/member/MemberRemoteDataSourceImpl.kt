package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.member.MemberBlockRequest
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse
import javax.inject.Inject

class MemberRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
) : MemberRemoteDataSource {
    override suspend fun getInvitationCode(agencyId: Long): Result<InvitationCodeResponse> {
        return moneyMongApi.getInvitationCode(agencyId)
    }

    override suspend fun reInvitationCode(agencyId: Long): Result<InvitationCodeResponse> {
        return moneyMongApi.reInvitationCode(agencyId)
    }

    override suspend fun getMemberLists(agencyId: Long): Result<MemberListResponse> {
        return moneyMongApi.getMemberLists(agencyId)
    }

    override suspend fun updateMemberAuthor(agencyId : Long, data: UpdateAuthorRequest): Result<Unit> {
        return moneyMongApi.updateMemberAuthor(agencyId,data)
    }

    override suspend fun blockMemberAuthor(agencyId: Long, data: MemberBlockRequest): Result<Unit> {
        return moneyMongApi.blockMemberAuthor(agencyId, data)
    }
}
package com.moneymong.moneymong.data.datasource.member

import com.moneymong.moneymong.network.api.AgencyApi
import com.moneymong.moneymong.network.api.MemberApi
import com.moneymong.moneymong.network.request.member.MemberBlockRequest
import com.moneymong.moneymong.network.request.member.UpdateAuthorRequest
import com.moneymong.moneymong.network.response.member.InvitationCodeResponse
import com.moneymong.moneymong.network.response.member.MemberListResponse
import javax.inject.Inject

class MemberRemoteDataSourceImpl @Inject constructor(
    private val memberApi: MemberApi,
    private val agencyApi: AgencyApi
) : MemberRemoteDataSource {
    override suspend fun getInvitationCode(agencyId: Long): Result<InvitationCodeResponse> {
        return agencyApi.getInvitationCode(agencyId)
    }

    override suspend fun reInvitationCode(agencyId: Long): Result<InvitationCodeResponse> {
        return agencyApi.reInvitationCode(agencyId)
    }

    override suspend fun getMemberLists(agencyId: Long): Result<MemberListResponse> {
        return memberApi.getMemberLists(agencyId)
    }

    override suspend fun updateMemberAuthor(agencyId : Long, data: UpdateAuthorRequest): Result<Unit> {
        return memberApi.updateMemberAuthor(agencyId,data)
    }

    override suspend fun blockMemberAuthor(agencyId: Long, data: MemberBlockRequest): Result<Unit> {
        return memberApi.blockMemberAuthor(agencyId, data)
    }
}
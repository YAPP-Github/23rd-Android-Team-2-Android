package com.moneymong.moneymong.data.repository.member

import com.moneymong.moneymong.data.datasource.member.MemberRemoteDataSource
import com.moneymong.moneymong.data.mapper.member.toEntity
import com.moneymong.moneymong.data.mapper.member.toRequest
import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.entity.member.MemberListEntity
import com.moneymong.moneymong.domain.param.member.MemberBlockParam
import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteDataSource: MemberRemoteDataSource
) : MemberRepository {
    override suspend fun getInvitationCode(agencyId: Long): Result<AgencyCodeEntity> {
        return memberRemoteDataSource.getInvitationCode(agencyId).map { it.toEntity() }
    }

    override suspend fun reInvitationCode(agencyId: Long): Result<AgencyCodeEntity> {
        return memberRemoteDataSource.reInvitationCode(agencyId).map { it.toEntity() }
    }

    override suspend fun getMemberLists(agencyId: Long): Result<MemberListEntity> {
        return memberRemoteDataSource.getMemberLists(agencyId).map { it.toEntity() }
    }

    override suspend fun updateMemberAuthor(agencyId : Long, data: UpdateAuthorParam): Result<Unit> {
        return memberRemoteDataSource.updateMemberAuthor(agencyId, data.toRequest())
    }

    override suspend fun blockMemberAuthor(data: MemberBlockParam): Result<Unit> {
        return memberRemoteDataSource.blockMemberAuthor(data.agencyId, data.toRequest())

    }
}
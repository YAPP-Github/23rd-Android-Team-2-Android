package com.moneymong.moneymong.data.repository.member

import com.moneymong.moneymong.data.datasource.member.MemberRemoteDataSource
import com.moneymong.moneymong.data.mapper.member.toEntity
import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
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
}
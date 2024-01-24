package com.moneymong.moneymong.domain.repository.member

import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity

interface MemberRepository {
    suspend fun getInvitationCode(agencyId: Long): Result<AgencyCodeEntity>
    suspend fun reInvitationCode(agencyId: Long): Result<AgencyCodeEntity>
}
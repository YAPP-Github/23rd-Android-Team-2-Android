package com.moneymong.moneymong.domain.repository.member

import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.entity.member.MemberListEntity

interface MemberRepository {
    suspend fun getInvitationCode(agencyId: Long): Result<AgencyCodeEntity>
    suspend fun reInvitationCode(agencyId: Long): Result<AgencyCodeEntity>
    suspend fun getMemberLists(agencyId: Long) : Result<MemberListEntity>
}
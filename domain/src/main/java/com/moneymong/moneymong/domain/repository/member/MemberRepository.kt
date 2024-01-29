package com.moneymong.moneymong.domain.repository.member

import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.entity.member.MemberListEntity
import com.moneymong.moneymong.domain.param.member.MemberBlockParam
import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam

interface MemberRepository {
    suspend fun getInvitationCode(agencyId: Long): Result<AgencyCodeEntity>
    suspend fun reInvitationCode(agencyId: Long): Result<AgencyCodeEntity>
    suspend fun getMemberLists(agencyId: Long) : Result<MemberListEntity>
    suspend fun updateMemberAuthor(agencyId : Long, data : UpdateAuthorParam) : Result<Unit>
    suspend fun blockMemberAuthor(data: MemberBlockParam) : Result<Unit>
}
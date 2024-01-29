package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class MemberInvitationCodeUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseUseCase<Long, Result<AgencyCodeEntity>>() {
    override suspend fun invoke(agencyId: Long): Result<AgencyCodeEntity> {
        return memberRepository.getInvitationCode(agencyId)
    }
}
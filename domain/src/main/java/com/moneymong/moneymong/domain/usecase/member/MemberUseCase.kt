package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.member.AgencyCodeEntity
import com.moneymong.moneymong.domain.param.member.AgencyIdParam
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class MemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
)
    : BaseUseCase<Long, Result<AgencyCodeEntity>>() {

    override suspend fun invoke(data: Long): Result<AgencyCodeEntity> {
       return memberRepository.getInvitationCode(data)
    }


}
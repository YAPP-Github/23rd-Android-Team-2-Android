package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.member.MemberListEntity
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class MemberListUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseUseCase<Long, Result<MemberListEntity>>() {

    override suspend fun invoke(agencyId: Long): Result<MemberListEntity> {
        return memberRepository.getMemberLists(agencyId)
    }
}
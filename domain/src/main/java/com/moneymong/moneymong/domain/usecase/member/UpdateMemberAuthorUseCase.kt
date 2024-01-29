package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.member.UpdateAuthorParam
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class UpdateMemberAuthorUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend fun invoke(agencyId : Long, data: UpdateAuthorParam): Result<Unit> {
        return memberRepository.updateMemberAuthor(agencyId , data)
    }
}
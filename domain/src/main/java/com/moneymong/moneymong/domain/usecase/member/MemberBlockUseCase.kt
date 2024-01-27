package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.param.member.MemberBlockParam
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class MemberBlockUseCase @Inject constructor(
    private val memberRepository: MemberRepository
){
    suspend fun invoke(agencyId : Long, data : MemberBlockParam) : Result<Unit> {
        return memberRepository.blockMemberAuthor(agencyId, data)
    }
}
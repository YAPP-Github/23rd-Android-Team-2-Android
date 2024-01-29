package com.moneymong.moneymong.domain.usecase.member

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.member.MemberBlockParam
import com.moneymong.moneymong.domain.repository.member.MemberRepository
import javax.inject.Inject

class MemberBlockUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseUseCase<MemberBlockParam, Result<Unit>>(){
    override suspend fun invoke(data : MemberBlockParam) : Result<Unit> {
        return memberRepository.blockMemberAuthor(data)
    }
}
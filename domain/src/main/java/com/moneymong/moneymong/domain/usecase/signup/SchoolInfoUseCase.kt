package com.moneymong.moneymong.domain.usecase.signup

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.TokenRepository
import javax.inject.Inject

class SchoolInfoUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) :BaseUseCase<Boolean, Unit>(){

    override suspend fun invoke(data: Boolean){
        tokenRepository.setSchoolInfoExist(data)
    }
}
package com.moneymong.moneymong.domain.usecase.login

import com.moneymong.moneymong.domain.repository.TokenRepository
import javax.inject.Inject

class TokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend fun getAccessToken(): Result<String> {
        return tokenRepository.getAccessToken()
    }

    suspend fun getSchoolInfo(): Result<Boolean> {
        return tokenRepository.getSchoolInfo()
    }

}
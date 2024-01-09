package com.moneymong.moneymong.domain.usecase.login

import com.moneymong.moneymong.domain.repository.LoginRepository
import com.moneymong.moneymong.domain.repository.TokenRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
) {
    suspend fun kakaoLogin() {
        loginRepository.kakaoLogin()
    }

    suspend fun getRefreshToken() :String {
        return tokenRepository.getRefreshToken()
    }
}
package com.moneymong.moneymong.domain.usecase.login

import com.moneymong.moneymong.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun kakaoLogin() {
        loginRepository.kakaoLogin()
    }
}
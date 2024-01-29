package com.moneymong.moneymong.domain.usecase.login

import com.moneymong.moneymong.domain.util.LoginCallback
import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) : BaseUseCase<LoginCallback, Unit>() {

    override suspend fun invoke(data: LoginCallback) {
        loginRepository.kakaoLogin(data)
    }

}
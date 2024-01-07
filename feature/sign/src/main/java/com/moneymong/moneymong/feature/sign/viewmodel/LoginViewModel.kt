package com.moneymong.moneymong.feature.sign.viewmodel

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.moneymong.moneymong.feature.sign.LoginSideEffect
import com.moneymong.moneymong.feature.sign.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    suspend fun onLoginButtonClicked() {
        loginUseCase.kakaoLogin()
    }
}
package com.moneymong.moneymong.feature.sign

import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.kakao.sdk.user.UserApiClient
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    suspend fun onLoginButtonClicked() {
        loginUseCase.kakaoLogin()
    }
}
package com.moneymong.moneymong.feature.sign.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.LoginCallback
import com.moneymong.moneymong.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.moneymong.moneymong.feature.sign.sideeffect.LoginSideEffect
import com.moneymong.moneymong.feature.sign.state.LoginState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {


    fun onLoginButtonClicked() = intent {
        viewModelScope.launch {
            loginUseCase.kakaoLogin(object : LoginCallback {
                override suspend fun onLoginSuccess() {
                    if(loginUseCase.getSchoolInfo()){
                        reduce {
                            state.copy(
                                isSchoolInfoExist = true
                            )
                        }
                    } else {
                        reduce {
                            state.copy(
                                isSchoolInfoExist = false
                            )
                        }
                    }

                }

                override suspend fun onLoginFailure(exception: Exception) {
                    //TODO 에러 화면
                    // 로그인 실패 처리
                    // 예: 상태 업데이트, 오류 메시지 표시 등
                }
            })
        }
    }
}
package com.moneymong.moneymong.feature.sign.viewmodel

import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.util.LoginCallback
import com.moneymong.moneymong.domain.usecase.login.LoginUseCase
import com.moneymong.moneymong.domain.usecase.login.TokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.moneymong.moneymong.feature.sign.sideeffect.LoginSideEffect
import com.moneymong.moneymong.feature.sign.state.LoginState
import com.moneymong.moneymong.network.util.TokenCallback
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenUseCase: TokenUseCase
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()), TokenCallback {

    fun onLoginButtonClicked() = intent {
        viewModelScope.launch {
            loginUseCase.invoke(object : LoginCallback {
                override suspend fun onLoginSuccess() {
                    tokenUseCase.getSchoolInfo()
                        .onSuccess {
                            if(it) {
                                reduce {
                                    state.copy(
                                        isSchoolInfoExist = true
                                    )
                                }
                            }
                            else{
                                reduce {
                                    state.copy(
                                        isSchoolInfoExist = false
                                    )
                                }
                            }
                        }.onFailure {
                            //TODO - 에러처리
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

    override suspend fun onTokenFailure() {
        intent {
            reduce {
                state.copy(
                    isLoginRequired = true
                )
            }
        }
    }

    fun isLoginRequiredChanged(boolean: Boolean) = intent {
        reduce {
            state.copy(
                isLoginRequired = boolean
            )
        }
    }
}
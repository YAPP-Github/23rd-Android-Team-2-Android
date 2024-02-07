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
    private val tokenUseCase: TokenUseCase,
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()), TokenCallback {

    fun onLoginButtonClicked() = intent {
        viewModelScope.launch {
            loginUseCase.invoke(object : LoginCallback {
                override suspend fun onLoginSuccess() {
                    //fetchMyInfo()
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
                            reduce {
                                state.copy(
                                    visibleError = true,
                                    errorMessage = "문제가 발생했습니다.\n다시 시도해주세요"
                                )
                            }
                        }
                }

                override suspend fun onLoginFailure(exception: Exception) {
                    // 로그인 실패
                    reduce {
                        state.copy(
                            visibleError = true,
                            errorMessage = "문제가 발생했습니다.\n다시 시도해주세요"
                        )
                    }
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

    fun visibleErrorChanged(isVisibleError: Boolean) = intent{
        reduce {
            state.copy(
                visibleError = isVisibleError,
            )
        }
    }

    fun isSchoolInfoExistChanged(isSchoolInfoExist : Boolean?) = intent{
        reduce {
            state.copy(
                isSchoolInfoExist = isSchoolInfoExist
            )
        }
    }
}
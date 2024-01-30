package com.moneymong.moneymong.feature.sign.viewmodel

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.login.TokenUseCase
import com.moneymong.moneymong.feature.sign.sideeffect.SplashSideEffect
import com.moneymong.moneymong.feature.sign.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCase
) : BaseViewModel<SplashState, SplashSideEffect>(
    SplashState()
) {

    fun startAnimationChanged(startAnimation: Boolean) = intent {
        reduce {
            state.copy(
                startAnimation = startAnimation
            )
        }
    }

    fun checkTokenValidity() = intent {
        tokenUseCase.getAccessToken()
            .onSuccess {
                if (it.isNotEmpty()) {
                    reduce {
                        state.copy(
                            isTokenValid = true
                        )
                    }
                } else {
                    reduce {
                        state.copy(
                            isTokenValid = false
                        )
                    }
                }
            }
            .onFailure {
                //TODO - 에러 화면
            }
    }

}
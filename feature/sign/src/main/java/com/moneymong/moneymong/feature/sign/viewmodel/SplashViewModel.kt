package com.moneymong.moneymong.feature.sign.viewmodel

import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.login.TokenUseCase
import com.moneymong.moneymong.feature.sign.sideeffect.SplashSideEffect
import com.moneymong.moneymong.feature.sign.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
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
        tokenUseCase.getDataStoreInfo()
            .onSuccess {
                Log.d("Splash", "${it.accessToken}, ${it.schoolInfoExist}")
                if (it.accessToken.isNotEmpty() && it.schoolInfoExist) {
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
                reduce {
                    state.copy(
                        isTokenValid = false
                    )
                }
            }
    }
}
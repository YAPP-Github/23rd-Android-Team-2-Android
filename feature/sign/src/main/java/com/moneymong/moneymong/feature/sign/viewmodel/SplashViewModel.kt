package com.moneymong.moneymong.feature.sign.viewmodel

import android.util.Base64
import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.login.LoginUseCase
import com.moneymong.moneymong.feature.sign.sideeffect.SplashSideEffect
import com.moneymong.moneymong.feature.sign.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
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
        val accessToken = loginUseCase.getAccessToken()
        if (accessToken.isNotEmpty()) {
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


}
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
                Log.d("accessToken, schoolInfoExist", "${it.accessToken},${it.schoolInfoExist}")
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
                //토큰 불러오기 실패 시 유저 정보가 없다고 간주하고 로그인화면으로 이동
                reduce {
                    state.copy(
                        isTokenValid = false
                    )
                }
            }
    }

}
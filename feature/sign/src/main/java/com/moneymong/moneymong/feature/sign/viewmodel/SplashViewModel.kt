package com.moneymong.moneymong.feature.sign.viewmodel

import androidx.lifecycle.ViewModel
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.feature.sign.sideeffect.SignUpSideEffect
import com.moneymong.moneymong.feature.sign.sideeffect.SplashSideEffect
import com.moneymong.moneymong.feature.sign.state.SignUpState
import com.moneymong.moneymong.feature.sign.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<SplashState, SplashSideEffect>(
    SplashState()
) {

    fun startAnimationChanged(startAnimation : Boolean) = intent{
        reduce {
            state.copy(
                startAnimation = startAnimation
            )
        }
    }
}
package com.moneymong.moneymong.feature.sign.state

import com.moneymong.moneymong.common.base.State

data class SplashState(
    val startAnimation: Boolean = false,
    val isTokenValid: Boolean? = null
) : State
package com.moneymong.moneymong.feature.sign.state

import com.moneymong.moneymong.common.base.State

data class LoginState (
    val isClickable : Boolean = false
) : State
package com.moneymong.moneymong.feature.sign.state

import com.moneymong.moneymong.common.base.State

data class SignCompleteState (
    val isCompleteBtnClicked : Boolean = false
) : State
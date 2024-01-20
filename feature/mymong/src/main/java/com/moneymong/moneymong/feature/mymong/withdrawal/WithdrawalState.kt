package com.moneymong.moneymong.feature.mymong.withdrawal

import com.moneymong.moneymong.common.base.State

data class WithdrawalState(
    val visibleWithdrawalDialog: Boolean = false,
    val isAgreed: Boolean = false,

    val visibleErrorDialog: Boolean = false,
    val errorMessage: String = ""
) : State
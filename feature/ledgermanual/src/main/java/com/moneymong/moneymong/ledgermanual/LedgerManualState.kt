package com.moneymong.moneymong.ledgermanual

import com.moneymong.moneymong.common.base.State

data class LedgerManualState(
    val isLoading: Boolean = false
): State

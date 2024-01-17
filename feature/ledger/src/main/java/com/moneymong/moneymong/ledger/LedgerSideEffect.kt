package com.moneymong.moneymong.ledger

import com.moneymong.moneymong.common.base.SideEffect

sealed class LedgerSideEffect : SideEffect {
    data class LedgerNavigateToLedgerDetail(val id: Int): LedgerSideEffect()
}

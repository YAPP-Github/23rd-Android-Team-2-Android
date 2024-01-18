package com.moneymong.moneymong.ledger

import com.moneymong.moneymong.common.base.SideEffect

sealed class LedgerSideEffect : SideEffect {
    data object LedgerOpenSheet : LedgerSideEffect()
    data object LedgerCloseSheet : LedgerSideEffect()
    data object LedgerNavigateToOCR : LedgerSideEffect()
    data class LedgerNavigateToLedgerDetail(val id: Int): LedgerSideEffect()
}

package com.moneymong.moneymong.ledgerdetail

import com.moneymong.moneymong.common.base.SideEffect

sealed class LedgerDetailSideEffect : SideEffect {
    data object LedgerDetailEdit : LedgerDetailSideEffect()
    data object LedgerDetailEditDone : LedgerDetailSideEffect()
}

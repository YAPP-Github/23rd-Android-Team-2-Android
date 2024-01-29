package com.moneymong.moneymong.ledgerdetail

import com.moneymong.moneymong.common.base.SideEffect

sealed class LedgerDetailSideEffect : SideEffect {
    data object LedgerDetailEdit : LedgerDetailSideEffect()
    data object LedgerDetailEditDone : LedgerDetailSideEffect()
    data object LedgerDetailOpenImagePicker : LedgerDetailSideEffect()
    data object LedgerDetailNavigateToLedger: LedgerDetailSideEffect()
    data object LedgerDetailConfirmModalNegative: LedgerDetailSideEffect()
    data object LedgerDetailConfirmModalPositive: LedgerDetailSideEffect()
    data class LedgerDetailFetchTransactionDetail(val detailId: Int) : LedgerDetailSideEffect()
}

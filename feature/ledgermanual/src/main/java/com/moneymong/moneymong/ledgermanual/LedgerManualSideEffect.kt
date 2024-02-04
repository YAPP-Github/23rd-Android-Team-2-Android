package com.moneymong.moneymong.ledgermanual

import com.moneymong.moneymong.common.base.SideEffect

sealed class LedgerManualSideEffect : SideEffect {

    data object LedgerManualOpenImagePicker : LedgerManualSideEffect()
    data object LedgerManualNavigateToLedger : LedgerManualSideEffect()
    data object LedgerManualShowPopBackStackModal : LedgerManualSideEffect()
    data object LedgerManualPostTransaction : LedgerManualSideEffect()
    data object LedgerManualHideKeyboard : LedgerManualSideEffect()
    data class LegerManualHidePopBackStackModal(val navigate: Boolean): LedgerManualSideEffect()
}

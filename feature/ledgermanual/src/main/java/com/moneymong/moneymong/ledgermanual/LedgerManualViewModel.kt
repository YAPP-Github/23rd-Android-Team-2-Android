package com.moneymong.moneymong.ledgermanual

import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LedgerManualViewModel @Inject constructor(

): BaseViewModel<LedgerManualState, LedgerManualSideEffect>(LedgerManualState()) {
}
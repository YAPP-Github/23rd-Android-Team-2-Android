package com.moneymong.moneymong.ledgerdetail

import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LedgerDetailViewModel @Inject constructor(

): BaseViewModel<LedgerDetailState, LedgerDetailSideEffect>(LedgerDetailState()) {

}
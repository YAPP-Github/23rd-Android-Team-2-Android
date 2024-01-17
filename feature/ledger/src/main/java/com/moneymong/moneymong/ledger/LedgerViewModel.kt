package com.moneymong.moneymong.ledger

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.usecase.ledger.FetchLedgerTransactionListUseCase
import com.moneymong.moneymong.ledger.view.LedgerTransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LedgerViewModel @Inject constructor(
    private val fetchLedgerTransactionListUseCase: FetchLedgerTransactionListUseCase
): BaseViewModel<LedgerState, LedgerSideEffect>(LedgerState()) {

    init {
        fetchLedgerTransactionList()
    }

    fun fetchLedgerTransactionList() = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val param = LedgerTransactionListParam(
                id = 1,
                year = state.currentDate.year,
                month = state.currentDate.monthValue,
                page = 0,
                limit = 10
            )
            fetchLedgerTransactionListUseCase(param)
                .onSuccess {
                    reduce { state.copy(ledgerTransaction = it) }
                }.onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun onChangeTransactionType(transactionType: LedgerTransactionType) = intent {
        reduce { state.copy(transactionType = transactionType) }
    }

    fun onAddMonthFromCurrentDate(addMonth: Long) = intent {
        val nextDate = state.currentDate.plusMonths(addMonth)
        reduce { state.copy(currentDate = nextDate) }
    }
}
package com.moneymong.moneymong.ledger

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.error.HttpError
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.usecase.agency.FetchAgencyIdUseCase
import com.moneymong.moneymong.domain.usecase.agency.FetchMyAgencyListUseCase
import com.moneymong.moneymong.domain.usecase.agency.SaveAgencyIdUseCase
import com.moneymong.moneymong.domain.usecase.ledger.FetchAgencyExistLedgerUseCase
import com.moneymong.moneymong.domain.usecase.ledger.FetchLedgerTransactionListUseCase
import com.moneymong.moneymong.ledger.navigation.LedgerArgs
import com.moneymong.moneymong.ledger.view.LedgerTransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@OptIn(OrbitExperimental::class)
@HiltViewModel
class LedgerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchLedgerTransactionListUseCase: FetchLedgerTransactionListUseCase,
    private val fetchAgencyExistLedgerUseCase: FetchAgencyExistLedgerUseCase,
    private val fetchMyAgencyListUseCase: FetchMyAgencyListUseCase,
    private val fetchAgencyIdUseCase: FetchAgencyIdUseCase,
    private val saveAgencyIdUseCase: SaveAgencyIdUseCase
) : BaseViewModel<LedgerState, LedgerSideEffect>(LedgerState()) {

    init {
        fetchAgencyId()
        fetchMyAgencyList()
        fetchAgencyExistLedger()
        fetchLedgerTransactionList()
        onChangeSnackbarState(visible = LedgerArgs(savedStateHandle).ledgerPostSuccess)
    }

    fun fetchAgencyId() = blockingIntent {
        val agencyId = fetchAgencyIdUseCase(Unit)
        reduce { state.copy(agencyId = agencyId) }
    }

    fun saveAgencyId(agencyId: Int) = blockingIntent {
        reduce { state.copy(agencyId = agencyId) }
        saveAgencyIdUseCase(agencyId)
    }

    fun fetchAgencyExistLedger() = intent {
        if (state.existAgency) {
            fetchAgencyExistLedgerUseCase(state.agencyId)
                .onSuccess {
                    reduce {
                        state.copy(
                            isExistLedger = it,
                            visibleError = false
                        )
                    }
                }
        }
    }

    fun fetchLedgerTransactionList() = intent {
        if (!state.isLoading && state.existAgency) {
            reduce { state.copy(isLoading = true) }
            val param = LedgerTransactionListParam(
                id = state.agencyId,
                year = state.currentDate.year,
                month = state.currentDate.monthValue,
                page = 0,
                limit = 10
            )
            fetchLedgerTransactionListUseCase(param)
                .onSuccess {
                    reduce {
                        state.copy(
                            ledgerTransaction = it,
                            visibleError = false
                        )
                    }
                }.onFailure {
                    reduce { state.copy(visibleError = true) }
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun fetchMyAgencyList() = intent {
        if (!state.isLoading && state.existAgency) {
            reduce { state.copy(isLoading = true) }
            fetchMyAgencyListUseCase(Unit)
                .onSuccess {
                    reduce { state.copy(agencyList = it) }
                }.onFailure {
                    reduce { state.copy(visibleError = true) }
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun reFetchLedgerData(agencyId: Int) {
        saveAgencyId(agencyId)
        fetchMyAgencyList()
        fetchAgencyExistLedger()
        fetchLedgerTransactionList()
    }

    fun onChangeTransactionType(transactionType: LedgerTransactionType) = intent {
        reduce { state.copy(transactionType = transactionType) }
    }

    fun onAddMonthFromCurrentDate(addMonth: Long) = intent {
        val nextDate = state.currentDate.plusMonths(addMonth)
        reduce { state.copy(currentDate = nextDate) }
    }

    fun onChangeSheetState(visible: Boolean) = intent {
        reduce { state.copy(showBottomSheet = visible) }
    }

    fun onChangeSnackbarState(visible: Boolean) = intent {
        reduce { state.copy(visibleSnackbar = visible) }
    }
}
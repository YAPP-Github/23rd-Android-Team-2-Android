package com.moneymong.moneymong.feature.agency.register.complete

import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgencyRegisterCompleteViewModel @Inject constructor() :
    BaseViewModel<AgencyRegisterCompleteState, AgencyRegisterCompleteSideEffect>(
        AgencyRegisterCompleteState
    ) {

    fun navigateToLedger() =
        eventEmit(sideEffect = AgencyRegisterCompleteSideEffect.NavigateToLedger)

    fun navigateToAgencySearch() =
        eventEmit(sideEffect = AgencyRegisterCompleteSideEffect.NavigateToAgencySearch)

    fun navigateToLedgerManual() =
        eventEmit(sideEffect = AgencyRegisterCompleteSideEffect.NavigateToLedgerManual)
}
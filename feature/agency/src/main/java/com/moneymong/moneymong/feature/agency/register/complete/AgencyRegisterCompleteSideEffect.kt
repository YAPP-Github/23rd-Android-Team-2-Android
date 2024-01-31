package com.moneymong.moneymong.feature.agency.register.complete

import com.moneymong.moneymong.common.base.SideEffect

sealed interface AgencyRegisterCompleteSideEffect : SideEffect {
    data object NavigateToAgencySearch : AgencyRegisterCompleteSideEffect
    data object NavigateToLedger : AgencyRegisterCompleteSideEffect
    data object NavigateToLedgerManual : AgencyRegisterCompleteSideEffect
}
package com.moneymong.moneymong.feature.agency.register

import com.moneymong.moneymong.common.base.SideEffect

sealed interface AgencyRegisterSideEffect : SideEffect {
    data object NavigateToComplete : AgencyRegisterSideEffect
    data object NavigateUp : AgencyRegisterSideEffect
}
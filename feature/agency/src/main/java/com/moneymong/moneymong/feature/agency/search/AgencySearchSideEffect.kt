package com.moneymong.moneymong.feature.agency.search

import com.moneymong.moneymong.common.base.SideEffect

sealed interface AgencySearchSideEffect : SideEffect {
    data object NavigateToRegister : AgencySearchSideEffect
    data class NavigateToJoin(val agencyId: Long) : AgencySearchSideEffect
}
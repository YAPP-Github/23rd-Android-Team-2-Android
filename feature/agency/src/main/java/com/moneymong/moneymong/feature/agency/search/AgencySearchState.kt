package com.moneymong.moneymong.feature.agency.search

import com.moneymong.moneymong.common.base.State

data class AgencySearchState(
    val joinedAgencies: List<Agency> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val visibleWarningDialog: Boolean = false,
) : State {

    val joinedAgenciesIds: List<Long>
        get() = joinedAgencies.map { it.id }
}
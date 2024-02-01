package com.moneymong.moneymong.feature.agency.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.error.MoneyMongError
import com.moneymong.moneymong.domain.usecase.agency.FetchMyAgencyListUseCase
import com.moneymong.moneymong.domain.usecase.agency.GetAgenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencySearchViewModel @Inject constructor(
    getAgenciesUseCase: GetAgenciesUseCase,
    private val fetchMyAgencyListUseCase: FetchMyAgencyListUseCase
) : BaseViewModel<AgencySearchState, AgencySearchSideEffect>(AgencySearchState()) {

    fun navigateToRegister() =
        eventEmit(sideEffect = AgencySearchSideEffect.NavigateToRegister)

    fun navigateToJoin(agencyId: Long) =
        eventEmit(sideEffect = AgencySearchSideEffect.NavigateToAgencyJoin(agencyId))

    val agencies = getAgenciesUseCase().map { pagingData ->
        pagingData.map {
            it.toAgency()
        }
    }.cachedIn(viewModelScope)

    init {
        fetchMyAgencyList()
    }

    fun fetchMyAgencyList() = intent {
        reduce {
            state.copy(
                isLoading = true,
                isError = false
            )
        }
        fetchMyAgencyListUseCase(Unit)
            .also {
                reduce { state.copy(isLoading = false) }
            }
            .onSuccess {
                reduce {
                    state.copy(
                        joinedAgencies = it.map { myAgencyEntity -> myAgencyEntity.toAgency() }
                    )
                }
            }.onFailure {
                reduce {
                    state.copy(
                        isError = true,
                        errorMessage = it.message ?: MoneyMongError.UnExpectedError.message,
                    )
                }
            }
    }


    fun changeVisibleWarningDialog(visible: Boolean) = intent {
        reduce {
            state.copy(visibleWarningDialog = visible)
        }
    }
}
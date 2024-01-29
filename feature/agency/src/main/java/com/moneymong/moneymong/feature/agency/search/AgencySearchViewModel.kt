package com.moneymong.moneymong.feature.agency.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.GetAgenciesUseCase
import com.moneymong.moneymong.feature.agency.search.component.AgencyBottomSheetType
import com.moneymong.moneymong.feature.agency.toAgency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencySearchViewModel @Inject constructor(
    getAgenciesUseCase: GetAgenciesUseCase,
) : BaseViewModel<AgencySearchState, AgencySearchSideEffect>(AgencySearchState()) {

    val agencies = getAgenciesUseCase().cachedIn(viewModelScope).map { pagingData ->
        pagingData.map {
            it.toAgency()
        }
    }

    fun changeVisibleBottomSheet(visible: Boolean) = intent {
        reduce {
            state.copy(
                visibleBottomSheet = visible
            )
        }
    }

    fun changeRegisterType(type: AgencyBottomSheetType?) = intent {
        reduce {
            state.copy(
                registerType = type
            )
        }
    }

    fun onDismissBottomSheet() = intent {
        reduce {
            state.copy(
                visibleBottomSheet = false,
                registerType = null
            )
        }
    }

    fun onBottomSheetConfirmButtonClicked() {
        eventEmit(AgencySearchSideEffect.NavigateToRegister)
        onDismissBottomSheet()
    }

    fun onAgencyItemClicked(agencyId: Long) {
        eventEmit(AgencySearchSideEffect.NavigateToJoin(agencyId = agencyId))
    }
}
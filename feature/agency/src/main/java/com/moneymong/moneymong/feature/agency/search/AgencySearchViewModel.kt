package com.moneymong.moneymong.feature.agency.search

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.feature.agency.search.component.AgencyBottomSheetType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencySearchViewModel @Inject constructor() :
    BaseViewModel<AgencySearchState, AgencySearchSideEffect>(AgencySearchState()) {

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
}
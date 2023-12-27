package com.moneymong.moneymong.feature.agency.register

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.feature.agency.AgencyType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyRegisterViewModel @Inject constructor() :
    BaseViewModel<AgencyRegisterState, AgencyRegisterSideEffect>(AgencyRegisterState()) {

    @OptIn(OrbitExperimental::class)
    fun onAgencyNameChanged(agencyName: TextFieldValue) = blockingIntent {
        reduce {
            state.copy(
                agencyName = agencyName
            )
        }
    }


    fun onAgencyTypeChanged(agencyType: AgencyType) = intent {
        reduce {
            state.copy(
                agencyType = agencyType
            )
        }
    }

    fun changeNameTextFieldIsError(isError: Boolean) = intent {
        reduce {
            state.copy(
                nameTextFieldIsError = isError
            )
        }
    }

    fun onRegisterButtonClicked() = intent {
        // todo: call register use case
        postSideEffect(AgencyRegisterSideEffect.NavigateToComplete)
    }


    fun changeVisibleDialog(visible: Boolean) = intent {
        reduce {
            state.copy(
                visibleOutDialog = visible
            )
        }
    }

    fun onDialogPositiveButtonClicked() {
        eventEmit(AgencyRegisterSideEffect.NavigateUp)
    }
}

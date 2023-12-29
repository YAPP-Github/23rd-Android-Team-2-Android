package com.moneymong.moneymong.feature.agency.register

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.param.AgencyRegisterParam
import com.moneymong.moneymong.domain.usecase.RegisterAgencyUseCase
import com.moneymong.moneymong.feature.agency.AgencyType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyRegisterViewModel @Inject constructor(
    private val registerAgencyUseCase: RegisterAgencyUseCase
) :
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
        registerAgencyUseCase(
            param = AgencyRegisterParam(
                name = state.agencyName.text,
                type = state.agencyType.toParam()
            )
        ).onSuccess {
            postSideEffect(AgencyRegisterSideEffect.NavigateToComplete)
        }.onFailure {
            // TODO: 에러 처라
        }
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

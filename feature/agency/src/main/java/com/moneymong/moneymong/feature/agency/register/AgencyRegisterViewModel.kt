package com.moneymong.moneymong.feature.agency.register

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.error.MoneyMongError
import com.moneymong.moneymong.domain.param.agency.AgencyRegisterParam
import com.moneymong.moneymong.domain.usecase.agency.RegisterAgencyUseCase
import com.moneymong.moneymong.domain.usecase.agency.SaveAgencyIdUseCase
import com.moneymong.moneymong.feature.agency.search.AgencyType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyRegisterViewModel @Inject constructor(
    private val registerAgencyUseCase: RegisterAgencyUseCase,
    private val saveAgencyIdUseCase: SaveAgencyIdUseCase
) : BaseViewModel<AgencyRegisterState, AgencyRegisterSideEffect>(AgencyRegisterState()) {

    fun navigateUp() = eventEmit(sideEffect = AgencyRegisterSideEffect.NavigateUp)

    fun registerAgency() = intent {
        registerAgencyUseCase(
            data = AgencyRegisterParam(
                name = state.agencyName.text,
                type = state.agencyType.toParam()
            )
        ).onSuccess {
            saveAgencyIdUseCase(it.id)
            postSideEffect(AgencyRegisterSideEffect.NavigateToComplete)
        }.onFailure {
            reduce {
                state.copy(
                    visibleErrorDialog = true,
                    errorMessage = it.message ?: MoneyMongError.UnExpectedError.message
                )
            }
        }
    }

    @OptIn(OrbitExperimental::class)
    fun changeAgencyName(agencyName: TextFieldValue) = blockingIntent {
        reduce {
            state.copy(
                agencyName = agencyName
            )
        }
    }

    fun changeAgencyType(agencyType: AgencyType) = intent {
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

    fun changeOutDialogVisibility(visible: Boolean) = intent {
        reduce {
            state.copy(
                visibleOutDialog = visible
            )
        }
    }

    fun changeErrorDialogVisibility(visible: Boolean) = intent {
        reduce {
            state.copy(visibleErrorDialog = visible)
        }
    }
}
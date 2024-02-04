package com.moneymong.moneymong.feature.mymong.withdrawal

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.error.MoneyMongError
import com.moneymong.moneymong.domain.usecase.agency.SaveAgencyIdUseCase
import com.moneymong.moneymong.domain.usecase.user.SaveDeniedCameraPermissionUseCase
import com.moneymong.moneymong.domain.usecase.user.SaveUserIdUseCase
import com.moneymong.moneymong.domain.usecase.user.WithdrawalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val withdrawalUseCase: WithdrawalUseCase,
    private val saveAgencyIdUseCase: SaveAgencyIdUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
    private val saveDeniedCameraPermissionUseCase: SaveDeniedCameraPermissionUseCase
) : BaseViewModel<WithdrawalState, WithdrawalSideEffect>(WithdrawalState()) {

    fun withdrawal() = intent {
        withdrawalUseCase(data = Unit)
            .onSuccess {
                clearLocalData()
                postSideEffect(WithdrawalSideEffect.NavigateToLogin)
            }.onFailure {
                reduce {
                    state.copy(
                        visibleErrorDialog = true,
                        visibleWithdrawalDialog = false,
                        errorMessage = it.message ?: MoneyMongError.UnExpectedError.message
                    )
                }
            }
    }

    fun toggleIsAgreed() = intent {
        reduce {
            state.copy(isAgreed = state.isAgreed.not())
        }
    }

    fun changeWithdrawalDialogVisibility(visible: Boolean) = intent {
        reduce {
            state.copy(visibleWithdrawalDialog = visible)
        }
    }

    fun changeErrorDialogVisibility(visible: Boolean) = intent {
        reduce {
            state.copy(visibleErrorDialog = visible)
        }
    }

    private suspend fun clearLocalData() {
        saveAgencyIdUseCase(0)
        saveUserIdUseCase(0)
        saveDeniedCameraPermissionUseCase(false)
    }
}
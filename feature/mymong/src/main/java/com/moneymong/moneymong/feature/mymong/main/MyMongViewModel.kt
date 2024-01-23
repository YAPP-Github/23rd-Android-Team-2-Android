package com.moneymong.moneymong.feature.mymong.main

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.error.MoneyMongError
import com.moneymong.moneymong.domain.usecase.GetMyInfoUseCase
import com.moneymong.moneymong.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MyMongViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel<MyMongState, MyMongSideEffect>(MyMongState()) {

    fun navigateToTermsOfUse() =
        eventEmit(sideEffect = MyMongSideEffect.NavigateToTermsOfUse)

    fun navigateToPriPolicyButton() =
        eventEmit(sideEffect = MyMongSideEffect.NavigateToPrivacyPolicy)

    fun navigateToWithdrawal() =
        eventEmit(sideEffect = MyMongSideEffect.NavigateToWithdrawal)

    init {
        getInfo()
    }

    private fun getInfo() = intent {
        getMyInfoUseCase(data = Unit)
            .also {
                reduce {
                    state.copy(isInfoLoading = false)
                }
            }
            .onSuccess {
                reduce {
                    state.copy(
                        name = it.name,
                        email = it.email,
                        university = it.university,
                        grade = it.grade
                    )
                }
            }.onFailure {
                reduce {
                    state.copy(
                        isInfoError = true,
                        infoErrorMessage = it.message ?: MoneyMongError.UnExpectedError.message
                    )
                }
            }
    }

    fun logout() = intent {
        logoutUseCase(data = Unit)
            .onSuccess {
                postSideEffect(sideEffect = MyMongSideEffect.NavigateToLogin)
            }
            .onFailure {
                reduce {
                    state.copy(
                        visibleErrorDialog = true,
                        visibleLogoutDialog = false,
                        logoutErrorMessage = it.message ?: MoneyMongError.UnExpectedError.message
                    )
                }
            }
    }

    fun changeLogoutDialogVisibility(visible: Boolean) = intent {
        reduce {
            state.copy(visibleLogoutDialog = visible)
        }
    }

    fun changeErrorDialogVisibility(visible: Boolean) = intent {
        reduce {
            state.copy(visibleErrorDialog = visible)
        }
    }
}
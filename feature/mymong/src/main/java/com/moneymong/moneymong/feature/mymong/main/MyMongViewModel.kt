package com.moneymong.moneymong.feature.mymong.main

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.GetMyInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MyMongViewModel @Inject constructor(
    private val getMyInfoUseCase: GetMyInfoUseCase
) : BaseViewModel<MyMongState, MyMongSideEffect>(MyMongState()) {

    init {
        getInfo()
    }

    private fun getInfo() = intent {
        getMyInfoUseCase()
            .also {
                reduce {
                    state.copy(isLoading = false)
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
                        isError = true
                    )
                }
            }
    }

    fun onClickTermsOfUse() {
        eventEmit(sideEffect = MyMongSideEffect.NavigateToTermsOfUse)
    }

    fun onClickPriPolicyButton() {
        eventEmit(sideEffect = MyMongSideEffect.NavigateToPrivacyPolicy)
    }

    fun onClickWithdrawal() {
        eventEmit(sideEffect = MyMongSideEffect.NavigateToWithdrawal)
    }

    fun changeVisibleLogoutDialog(visible: Boolean) = intent {
        reduce {
            state.copy(
                visibleLogoutDialog = visible
            )
        }
    }
}
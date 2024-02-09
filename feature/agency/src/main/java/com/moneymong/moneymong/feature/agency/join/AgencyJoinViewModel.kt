package com.moneymong.moneymong.feature.agency.join

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.agency.AgencyJoinUseCase
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import com.moneymong.moneymong.domain.usecase.agency.SaveAgencyIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyJoinViewModel @Inject constructor(
    private val useCase: AgencyJoinUseCase,
    private val saveAgencyIdUseCase: SaveAgencyIdUseCase
) : BaseViewModel<AgencyJoinState, AgencyJoinSideEffect>(AgencyJoinState()) {

    fun agencyCodeNumbers(agencyId: Long) = intent {
        val codeNumbers = state.numbers.joinToString(separator = "")
        viewModelScope.launch {
            useCase.invoke(agencyId, AgencyJoinParam(codeNumbers))
                .onSuccess {
                    if (it.certified) {
                        saveAgencyIdUseCase(agencyId.toInt())
                        reduce {
                            state.copy(
                                isError = false,
                                codeAccess = true
                            )
                        }
                    } else {
                        reduce {
                            state.copy(
                                isError = true
                            )
                        }
                    }
                }
                .onFailure {
                    reduce {
                        state.copy(
                            visiblePopUpError = true,
                            errorPopUpMessage = it.message.toString()
                        )
                    }

                }
        }

    }


    fun onIsErrorChanged(newIsError: Boolean) = intent {
        reduce {
            state.copy(
                isError = newIsError
            )
        }
    }

    fun onIsNumbersChanged(index: Int, value: String) = intent {
        val newNumbers = state.numbers.toMutableList().apply {
            this[index] = value
        }
        reduce {
            state.copy(
                numbers = newNumbers
            )
        }
    }

    fun resetNumbers() = intent {
        reduce {
            state.copy(
                numbers = List(6) { "" }
            )
        }
    }

    fun visiblePopUpErrorChanged(visibleError: Boolean) = intent {
        reduce {
            state.copy(
                visiblePopUpError = visibleError,
            )
        }
    }
}
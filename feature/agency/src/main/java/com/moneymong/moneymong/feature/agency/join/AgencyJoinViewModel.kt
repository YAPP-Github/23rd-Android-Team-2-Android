package com.moneymong.moneymong.feature.agency.join

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.AgencyJoinUseCase
import com.moneymong.moneymong.domain.param.agency.AgencyJoinParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyJoinViewModel @Inject constructor(
    private val useCase: AgencyJoinUseCase
) : BaseViewModel<AgencyJoinState, AgencyJoinSideEffect>(AgencyJoinState()) {

    fun agencyCodeNumbers(agencyId: Long) = intent {
        val codeNumbers =  state.numbers.joinToString(separator = "")
        viewModelScope.launch {
            useCase.invoke(AgencyJoinParam(agencyId, codeNumbers))
                .onSuccess {
                    if (it.certified) {
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
                    //TODO - 에러화면

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
        Log.d("newNumbers", newNumbers.toString())
        reduce {
            state.copy(
                numbers = newNumbers
            )
        }
        Log.d("numbers", state.numbers.joinToString(separator = ""))
    }

    fun resetNumbers() = intent {
        reduce {
            state.copy(
                numbers = List(6) { "" }
            )
        }
    }
}
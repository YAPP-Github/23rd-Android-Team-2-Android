package com.moneymong.moneymong.feature.agency.join

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.AgencyJoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class AgencyJoinViewModel @Inject constructor(
    private val useCase: AgencyJoinUseCase
) : BaseViewModel<AgencyJoinState, AgencyJoinSideEffect>(AgencyJoinState()) {

    fun agencyCodeNumbers(agencyId: Int, codeNumbers: String) = intent {
        viewModelScope.launch {
            useCase.agencyCodeNumbers(agencyId, codeNumbers)
                .onSuccess {
                    Log.d("success", it.toString())
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
                    Log.d("failure", it.message.toString())
                    //TODO - 에러화면
                }
        }

    }

    fun compareError() = intent {
        //TODO 초대코드 인증
        val allNumbersEntered = state.numbers.none { it.isEmpty() }
        if (allNumbersEntered && state.numbers.joinToString { "," } != "000000") {
            reduce {
                state.copy(

                )
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
        Log.d("index" , index.toString())
        Log.d("value", value)
        val newNumbers = state.numbers.toMutableList().apply {
            this[index] = value
        }
        Log.d("newNumbers", newNumbers.toString())
        reduce {
            state.copy(
                numbers = newNumbers
            )
        }
        Log.d("numbers", state.numbers.joinToString {","}.trim())
    }

    fun resetNumbers() = intent {
        reduce {
            state.copy(
                numbers = List(6) { "" }
            )
        }
    }
}
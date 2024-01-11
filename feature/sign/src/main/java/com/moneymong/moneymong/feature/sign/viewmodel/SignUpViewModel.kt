package com.moneymong.moneymong.feature.sign.viewmodel

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.param.signup.UnivParam
import com.moneymong.moneymong.domain.usecase.signup.UnivUseCase
import com.moneymong.moneymong.feature.sign.sideeffect.SignUpSideEffect
import com.moneymong.moneymong.feature.sign.state.SignUpState
import com.moneymong.moneymong.feature.sign.util.Grade
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val univUseCase: UnivUseCase
) : BaseViewModel<SignUpState, SignUpSideEffect>(SignUpState()) {

    fun userUniv() = intent {
        univUseCase.userUniv()
            .onSuccess {
                Log.d("University success", it.toString())
            }
            .onFailure {
                Log.d("University failure", it.message.toString())
                reduce {
                    state.copy(
                        isError = true
                    )
                }
            }
    }

    fun createUniv(universityName: String, grade: Int) = intent {
        val body = UnivParam(universityName, grade)
        univUseCase.createUniv(body)
            .onSuccess {
                Log.d("success", it.toString())
                reduce {
                    state.copy(
                        isUnivCreated = true
                    )
                }
            }
            .onFailure {
                Log.d("failure", it.message.toString())
                //TODO 에러화면
                reduce {
                    state.copy(
                        isError = true
                    )
                }

            }
    }

    fun searchUniv(searchQuery: String) = intent {
        univUseCase.searchUniv(searchQuery)
            .onSuccess {
                Log.d("Success", it.universities.toString())
                reduce {
                    state.copy(
                        universityResponse = it
                    )
                }
            }.onFailure {
                Log.d("failure", it.message.toString())
                //TODO 에러화면
                reduce {
                    state.copy(
                        isError = true
                    )
                }
            }
    }

    fun isSelectedChanged(isSelected: Boolean) = intent {
        reduce {
            state.copy(
                isSelected = isSelected
            )
        }
    }

    fun selectedUnivChanged(selectedUniv: String) = intent {
        reduce {
            state.copy(
                selectedUniv = selectedUniv
            )
        }
    }

    fun textValueChanged(textValue: TextFieldValue) = intent {
        reduce {
            state.copy(
                textValue = textValue
            )
        }
    }

    fun isEnabledChanged(isEnable: Boolean) = intent {
        reduce {
            state.copy(
                isEnabled = isEnable
            )
        }
    }

    fun subTitleStateChanged(subTitleState: Boolean) = intent {
        reduce {
            state.copy(
                subTitleState = subTitleState
            )
        }
    }

    fun gradeInforChanged(gradeInfor: Int) = intent {
        reduce {
            state.copy(
                gradeInfor = gradeInfor
            )
        }
    }


    fun isFilledChanged(isFilled: Boolean) = intent {
        reduce {
            state.copy(
                isFilled = isFilled
            )
        }
    }

    fun isListVisibleChanged(isListVisible: Boolean) = intent {
        reduce {
            state.copy(
                isListVisible = isListVisible
            )
        }
    }

    fun isItemSelectedChanged(isItemSelected: Boolean) = intent {
        reduce {
            state.copy(
                isItemSelected = isItemSelected
            )
        }
    }

    fun selectedGradeChange(selectedGrade: Grade?) = intent {
        reduce {
            state.copy(
                selectedGrade = selectedGrade
            )
        }
    }
}
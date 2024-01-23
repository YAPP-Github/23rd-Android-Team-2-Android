package com.moneymong.moneymong.feature.sign.viewmodel

import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.feature.sign.sideeffect.SignCompleteSideEffect
import com.moneymong.moneymong.feature.sign.state.SignCompleteState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SignCompleteViewModel @Inject constructor() :
    BaseViewModel<SignCompleteState, SignCompleteSideEffect>(SignCompleteState()) {

}
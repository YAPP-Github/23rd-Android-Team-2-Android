package com.moneymong.moneymong.feature.sign.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.moneymong.moneymong.feature.sign.sideeffect.LoginSideEffect
import com.moneymong.moneymong.feature.sign.state.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    fun onLoginButtonClicked()  = intent{
        //viewModelScope.launch {
            coroutineScope {
                launch {
                    loginUseCase.kakaoLogin()
                }.join()
                Log.d("확인", "schoolInfoExist 가져 왔다.")
                reduce {
                    state.copy(
                        isSchoolInfoExist = true
                    )
                }

            }
        // }
    }
}
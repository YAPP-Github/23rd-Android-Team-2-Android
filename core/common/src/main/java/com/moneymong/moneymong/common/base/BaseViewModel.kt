package com.moneymong.moneymong.common.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<S: State, E: SideEffect>(state: S) : ContainerHost<S, E>, ViewModel() {

    override val container = container<S, E>(state)

    fun eventEmit(sideEffect: E) = intent {
        postSideEffect(sideEffect = sideEffect)
    }
}
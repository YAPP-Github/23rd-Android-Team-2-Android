package com.moneymong.moneymong.feature.mymong.main

import com.moneymong.moneymong.common.base.SideEffect

sealed interface MyMongSideEffect : SideEffect {
    data object NavigateToWithdrawal : MyMongSideEffect
    data object NavigateToPrivacyPolicy : MyMongSideEffect
    data object NavigateToTermsOfUse : MyMongSideEffect
    data object NavigateToLogin : MyMongSideEffect
}
package com.moneymong.moneymong.feature.mymong.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.main.MyMongScreen

const val mymongRoute = "mymong_route"

internal fun NavGraphBuilder.myMongScreen(
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    composable(route = mymongRoute) {
        MyMongScreen(
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
            navigateToWithdrawal = navigateToWithdrawal,
            navigateToLogin = navigateToLogin,
        )
    }
}

fun NavGraphBuilder.myMongNavGraph(
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateUp: () -> Unit
) {
    myMongScreen(
        navigateToTermsOfUse = navigateToTermsOfUse,
        navigateToPrivacyPolicy = navigateToPrivacyPolicy,
        navigateToWithdrawal = navigateToWithdrawal,
        navigateToLogin = navigateToLogin,
    )
    privacyPolicyScreen(navigateUp = navigateUp)

    termsOfUseScreen(navigateUp = navigateUp)

    withdrawalScreen(
        navigateToLogin = navigateToLogin,
        navigateUp = navigateUp
    )
}
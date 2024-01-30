package com.moneymong.moneymong.feature.mymong.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.main.MyMongScreen

const val mymongRoute = "mymong_route"

fun NavController.navigateMyMong(
    navOptions: NavOptions? = null
) {
    navigate(mymongRoute, navOptions)
}

internal fun NavGraphBuilder.myMongScreen(
    padding: PaddingValues,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    composable(route = mymongRoute) {
        MyMongScreen(
            modifier = Modifier.padding(padding),
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy,
            navigateToWithdrawal = navigateToWithdrawal,
            navigateToLogin = navigateToLogin,
        )
    }
}

fun NavGraphBuilder.myMongNavGraph(
    padding: PaddingValues,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToWithdrawal: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateUp: () -> Unit
) {
    myMongScreen(
        padding = padding,
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
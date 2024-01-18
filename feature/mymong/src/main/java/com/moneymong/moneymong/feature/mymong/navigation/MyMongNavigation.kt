package com.moneymong.moneymong.feature.mymong.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.main.MyMongScreen

const val mymongRoute = "mymong_route"

internal fun NavGraphBuilder.myMongScreen(
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit
) {
    composable(route = mymongRoute) {
        MyMongScreen(
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy
        )
    }
}

fun NavGraphBuilder.myMongNavGraph(
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateUp: () -> Unit
) {
    myMongScreen(
        navigateToTermsOfUse = navigateToTermsOfUse,
        navigateToPrivacyPolicy = navigateToPrivacyPolicy
    )
    privacyPolicyScreen(navigateUp = navigateUp)

    termsOfUseScreen(navigateUp = navigateUp)
}
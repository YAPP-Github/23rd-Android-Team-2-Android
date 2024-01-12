package com.moneymong.moneymong.feature.mymong.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.main.MyMongScreen

const val mymongRoute = "mymong_route"

fun NavGraphBuilder.mymongScreen(
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
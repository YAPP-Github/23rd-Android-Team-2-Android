package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.SplashScreen

const val splashRoute = "splash_route"


fun NavGraphBuilder.splashScreen(
    navigateToLedger: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(route = splashRoute) {
        SplashScreen(
            navigateToLedger = navigateToLedger,
            navigateToLogin = navigateToLogin
        )
    }
}


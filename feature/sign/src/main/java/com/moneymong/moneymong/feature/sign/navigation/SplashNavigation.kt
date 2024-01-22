package com.moneymong.moneymong.feature.sign.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.LoginScreen
import com.moneymong.moneymong.feature.sign.SplashScreen

const val splashRoute = "splash_route"


fun NavGraphBuilder.splashScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(route = splashRoute) {
        SplashScreen(
            //modifier = Modifier.padding(padding),
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToLogin
        )
    }
}


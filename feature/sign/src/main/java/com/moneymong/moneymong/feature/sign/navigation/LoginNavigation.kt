package com.moneymong.moneymong.feature.sign.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.LoginScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.moneymong.moneymong.feature.sign.SplashScreen

const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(route = loginRoute) {
        LoginScreen(
            navigateToSignup = navigateToSignUp,
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToLogin
        )
    }
}


package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.LoginScreen

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


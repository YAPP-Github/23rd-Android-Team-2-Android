package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.SignUpScreen

const val signUpRoute = "signup_route"

fun NavController.navigateSignUp(navOptions: NavOptions? = null) {
    navigate(route = signUpRoute, navOptions = navOptions)
}

fun NavGraphBuilder.signUpScreen(
    navigateToSignComplete: () -> Unit,
    navigateUp: () -> Unit
) {
    composable(route = signUpRoute) {
        SignUpScreen(
            navigateToSignComplete = navigateToSignComplete,
            navigateUp = navigateUp
        )
    }
}

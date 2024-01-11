package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.SignUpScreen

const val signUpRoute = "signup_route"

fun NavGraphBuilder.signUpScreen(navController: NavHostController) {
    composable(route = signUpRoute) {
        SignUpScreen(navController = navController)
    }
}

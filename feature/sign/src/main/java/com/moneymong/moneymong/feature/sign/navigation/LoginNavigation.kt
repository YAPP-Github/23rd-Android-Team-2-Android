package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.LoginScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController

const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen(navController: NavHostController) {
    composable(route = loginRoute) {
        LoginScreen(navController = navController)
    }
}

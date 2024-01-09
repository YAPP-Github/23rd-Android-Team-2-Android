package com.moneymong.moneymong.feature.sign.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moneymong.moneymong.feature.sign.LoginScreen

const val loginRoute = "login_route"


fun NavGraphBuilder.loginScreen() {
    composable(route = loginRoute) {
        LoginScreen()
    }
}

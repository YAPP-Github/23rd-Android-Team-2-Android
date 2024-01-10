package com.moneymong.moneymong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moneymong.moneymong.feature.sign.navigation.loginRoute
import com.moneymong.moneymong.feature.sign.navigation.loginScreen
import com.moneymong.moneymong.feature.sign.navigation.splashRoute
import com.moneymong.moneymong.feature.sign.navigation.splashScreen
import com.moneymong.moneymong.home.HomeScreen
import com.moneymong.moneymong.home.navigation.homeRoute
import com.moneymong.moneymong.home.navigation.homeScreen

@Composable
fun MoneyMongNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        splashScreen(navController)
        loginScreen()
        homeScreen()

    }
}
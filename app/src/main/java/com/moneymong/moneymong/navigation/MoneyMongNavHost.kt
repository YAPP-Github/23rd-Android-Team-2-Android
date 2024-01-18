package com.moneymong.moneymong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moneymong.moneymong.feature.agency.join.navigation.agencyCompleteScreen
import com.moneymong.moneymong.feature.agency.join.navigation.agencyJoinScreen
import com.moneymong.moneymong.home.navigation.homeRoute
import com.moneymong.moneymong.home.navigation.homeScreen

@Composable
fun MoneyMongNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute // TODO
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen()
        agencyJoinScreen(navController)
        agencyCompleteScreen(navController)
    }
}
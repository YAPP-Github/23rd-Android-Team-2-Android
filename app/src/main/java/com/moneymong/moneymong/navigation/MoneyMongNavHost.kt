package com.moneymong.moneymong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MoneyMongNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "" // TODO
) {
    val navController = rememberNavController()
//    NavHost(
//        modifier = modifier,
//        navController = navController,
//        startDestination = startDestination
//    ) {
//
//    }
}
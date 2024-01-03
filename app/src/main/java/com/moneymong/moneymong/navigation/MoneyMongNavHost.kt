package com.moneymong.moneymong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moneymong.moneymong.home.HomeScreen
import com.moneymong.moneymong.home.navigation.homeRoute
import com.moneymong.moneymong.home.navigation.homeScreen
import com.moneymong.moneymong.ocr.navigation.ocrRoute
import com.moneymong.moneymong.ocr.navigation.ocrScreen
import com.moneymong.moneymong.ocr_result.navigation.navigateToOCRResult
import com.moneymong.moneymong.ocr_result.navigation.ocrResultScreen

@Composable
fun MoneyMongNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = ocrRoute // TODO
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(navController = navController)
        ocrScreen(navigateToOCRResult = navController::navigateToOCRResult)
        ocrResultScreen(navController = navController)
    }
}
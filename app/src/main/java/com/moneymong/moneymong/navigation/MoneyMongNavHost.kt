package com.moneymong.moneymong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moneymong.moneymong.home.navigation.homeRoute
import com.moneymong.moneymong.home.navigation.homeScreen
import com.moneymong.moneymong.home.navigation.navigateToHome
import com.moneymong.moneymong.ocr.navigation.ocrRoute
import com.moneymong.moneymong.ocr.navigation.ocrScreen
import com.moneymong.moneymong.ocr_detail.navigation.navigateToOCRDetail
import com.moneymong.moneymong.ocr_detail.navigation.ocrDetailScreen
import com.moneymong.moneymong.ocr_result.navigation.navigateToOCRResult
import com.moneymong.moneymong.ocr_result.navigation.ocrResultScreen

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
        homeScreen(navController = navController)
        ocrScreen(navigateToOCRResult = navController::navigateToOCRResult)
        ocrResultScreen(
            navigateToHome = { _, b ->
                navController.navigateToHome(navOptions {
                    popUpTo(
                        navController.graph.id
                    ) { inclusive = true }
                }, b)
            },
            navigateToOCRDetail = navController::navigateToOCRDetail,
            popBackStack = navController::popBackStack
        )
        ocrDetailScreen(
            navigateToHome = { _, b ->
                navController.navigateToHome(navOptions {
                    popUpTo(
                        navController.graph.id
                    ) { inclusive = true }
                }, b)
            },
            popBackStack = navController::popBackStack
        )
    }
}
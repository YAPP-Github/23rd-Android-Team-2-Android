package com.moneymong.moneymong.ocr_result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.ocr_result.OCRResultScreen

const val ocrResultRoute = "ocrresult_route"

fun NavController.navigateToOCRResult(navOptions: NavOptions? = null) {
    this.navigate(ocrResultRoute, navOptions)
}

fun NavGraphBuilder.ocrResultScreen(
    navController: NavController
) {
    composable(route = ocrResultRoute) {
        OCRResultScreen(navController = navController)
    }
}
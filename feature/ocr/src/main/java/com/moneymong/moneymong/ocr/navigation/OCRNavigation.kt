package com.moneymong.moneymong.ocr.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.ocr.OCRScreen

const val ocrRoute = "ocr_route"

fun NavController.navigateOCR(navOptions: NavOptions? = null) {
    this.navigate(ocrRoute, navOptions)
}

fun NavGraphBuilder.ocrScreen(
    navigateToOCRResult: (navOptions: NavOptions?, document: String) -> Unit,
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    popBackStack: () -> Unit
) {
    composable(route = ocrRoute) {
        OCRScreen(
            navigateToOCRResult = navigateToOCRResult,
            navigateToLedger = navigateToLedger,
            popBackStack = popBackStack
        )
    }
}
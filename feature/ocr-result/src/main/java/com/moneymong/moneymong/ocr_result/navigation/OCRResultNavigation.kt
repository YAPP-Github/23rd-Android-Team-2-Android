package com.moneymong.moneymong.ocr_result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.ocr_result.OCRResultScreen
import java.net.URLEncoder

const val ocrResultRoute = "ocrresult_route?document={document}"

fun NavController.navigateToOCRResult(
    navOptions: NavOptions? = null,
    document: String
) {
    this.navigate(ocrResultRoute.replace("{document}", document), navOptions)
}

fun NavGraphBuilder.ocrResultScreen(
    popBackStack: () -> Unit
) {
    composable(route = ocrResultRoute) { backStackEntry ->
        val documentJson = backStackEntry.arguments?.getString("document")
        val documentEntity = documentJson?.let { Gson().fromJson(it, DocumentEntity::class.java) }

        OCRResultScreen(
            document = documentEntity,
            popBackStack = popBackStack
        )
    }
}
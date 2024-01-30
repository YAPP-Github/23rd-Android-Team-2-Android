package com.moneymong.moneymong.ocr_result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.ocr_result.OCRResultScreen

const val ocrResultRoute = "ocrresult_route?document={document}"

fun NavController.navigateOCRResult(
    navOptions: NavOptions? = null,
    document: String
) {
    this.navigate(ocrResultRoute.replace("{document}", document), navOptions)
}

fun NavGraphBuilder.ocrResultScreen(
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    popBackStack: () -> Unit,
    navigateToOCRDetail: (navOptions: NavOptions?, document: String) -> Unit
) {
    composable(route = ocrResultRoute) { backStackEntry ->
        val match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z~!@#$%^&*()_+|<>?:{}]"
        val documentJson = backStackEntry.arguments?.getString("document")
        val documentEntity =
            documentJson?.let { Gson().fromJson(it.replace(match, ""), DocumentEntity::class.java) }

        OCRResultScreen(
            document = documentEntity,
            navigateToLedger = navigateToLedger,
            navigateToOCRDetail = navigateToOCRDetail,
            popBackStack = popBackStack
        )
    }
}
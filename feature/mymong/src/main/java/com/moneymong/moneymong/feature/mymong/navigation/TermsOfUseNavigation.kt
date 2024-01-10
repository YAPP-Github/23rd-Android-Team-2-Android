package com.moneymong.moneymong.feature.mymong.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.termsofuse.TermsOfUseScreen

const val termsOfUseRoute = "termsOfUse_route"

fun NavController.navigateTermsOfUse(navOptions: NavOptions? = null) {
    navigate(termsOfUseRoute, navOptions)
}

fun NavGraphBuilder.termsOfUseScreen(
    padding: PaddingValues,
    navigateUp: () -> Unit
) {
    composable(route = termsOfUseRoute) {
        TermsOfUseScreen(
            modifier = Modifier.padding(padding),
            navigateUp = navigateUp
        )
    }
}
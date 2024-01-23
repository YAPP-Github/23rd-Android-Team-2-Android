package com.moneymong.moneymong.feature.mymong.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.termsofuse.TermsOfUseScreen

const val termsOfUseRoute = "termsOfUse_route"

fun NavController.navigateTermsOfUse(navOptions: NavOptions? = null) {
    navigate(termsOfUseRoute, navOptions)
}

internal fun NavGraphBuilder.termsOfUseScreen(navigateUp: () -> Unit) {
    composable(route = termsOfUseRoute) {
        TermsOfUseScreen(navigateUp = navigateUp)
    }
}
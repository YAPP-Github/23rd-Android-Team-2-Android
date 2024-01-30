package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.SignCompleteScreen


const val signCompleteRoute = "signComplete_Route"

fun NavController.navigateSignComplete(navOptions: NavOptions? = null) {
    navigate(route = signCompleteRoute, navOptions = navOptions)
}

fun NavGraphBuilder.signCompleteScreen(
    navigateToLedger: () -> Unit
) {
    composable(route = signCompleteRoute) {
        SignCompleteScreen(
            navigateToLedger = navigateToLedger
        )
    }
}
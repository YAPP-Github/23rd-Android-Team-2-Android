package com.moneymong.moneymong.feature.sign.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.sign.SignCompleteScreen


const val signCompleteRoute = "signComplete_Route"

fun NavGraphBuilder.signCompleteScreen(
    navigateToHome: () -> Unit
) {
    composable(route = signCompleteRoute) {
        SignCompleteScreen(
            navigateToHome = navigateToHome
        )
    }
}
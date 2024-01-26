package com.moneymong.moneymong.feature.mymong.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.withdrawal.WithdrawalScreen

const val withdrawalRoute = "widthdrawal_route"

fun NavController.navigateWithdrawal(navOptions: NavOptions? = null) {
    navigate(withdrawalRoute, navOptions)
}

internal fun NavGraphBuilder.withdrawalScreen(
    navigateToLogin: () -> Unit,
    navigateUp: () -> Unit
) {
    composable(route = withdrawalRoute) {
        WithdrawalScreen(
            navigateToLogin = navigateToLogin,
            navigateUp = navigateUp
        )
    }
}
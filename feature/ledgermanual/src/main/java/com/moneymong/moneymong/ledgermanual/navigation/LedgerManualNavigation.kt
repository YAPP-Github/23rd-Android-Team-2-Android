package com.moneymong.moneymong.ledgermanual.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.ledgermanual.LedgerManualScreen

const val ledgerManualRoute = "ledgerManual_route"

fun NavController.navigateLedgerManual(
    navOptions: NavOptions? = null
) {
    this.navigate("ledgerManual_route", navOptions)
}

fun NavGraphBuilder.ledgerManualScreen(
    popBackStack: () -> Unit,
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit
) {
    composable(
        route = ledgerManualRoute
    ) {
        LedgerManualScreen(
            popBackStack = popBackStack,
            navigateToLedger = navigateToLedger
        )
    }
}
package com.moneymong.moneymong.ledger.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.moneymong.moneymong.ledger.LedgerScreen

const val ledgerRoute = "ledger_route"
const val LEDGER_POST_SUCCESS = "LedgerPostSuccess"
const val ledgerRouteWithArgs = "${ledgerRoute}/{${LEDGER_POST_SUCCESS}}"

private val arguments = listOf(
    navArgument(LEDGER_POST_SUCCESS) {
        type = NavType.BoolType
        defaultValue = false
    }
)

fun NavController.navigateLedger(
    ledgerPostSuccess: Boolean = false,
    navOptions: NavOptions? = navOptions {
        popUpTo(graph.id) { inclusive = true }
    },
) {
    this.navigate("${ledgerRoute}/${ledgerPostSuccess}", navOptions)
}

fun NavGraphBuilder.ledgerScreen(
    navigateToAgency: () -> Unit,
    navigateToOCR: (NavOptions?) -> Unit,
    navigateToLedgerDetail: (NavOptions?, Int) -> Unit,
    navigateToLedgerManual: (NavOptions?) -> Unit
) {
    composable(
        route = ledgerRouteWithArgs,
        arguments = arguments
    ) { backStackEntry ->
        val ledgerPostSuccess = backStackEntry.arguments?.getBoolean(LEDGER_POST_SUCCESS) ?: false
        LedgerScreen(
            ledgerPostSuccess = ledgerPostSuccess,
            navigateToAgency = navigateToAgency,
            navigateToOCR = navigateToOCR,
            navigateToLedgerDetail = navigateToLedgerDetail,
            navigateToLedgerManual = navigateToLedgerManual
        )
    }
}
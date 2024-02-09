package com.moneymong.moneymong.ledger.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.lifecycle.SavedStateHandle
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
    padding: PaddingValues,
    navigateToAgency: () -> Unit,
    navigateToOCR: (NavOptions?) -> Unit,
    navigateToLedgerDetail: (NavOptions?, Int, Boolean) -> Unit,
    navigateToLedgerManual: (NavOptions?) -> Unit
) {
    composable(
        route = ledgerRouteWithArgs,
        arguments = arguments
    ) {
        LedgerScreen(
            padding = padding,
            navigateToAgency = navigateToAgency,
            navigateToOCR = navigateToOCR,
            navigateToLedgerDetail = navigateToLedgerDetail,
            navigateToLedgerManual = navigateToLedgerManual
        )
    }
}

internal class LedgerArgs(val ledgerPostSuccess: Boolean) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        ledgerPostSuccess = checkNotNull(savedStateHandle[LEDGER_POST_SUCCESS])
    )
}
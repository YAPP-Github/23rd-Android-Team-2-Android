package com.moneymong.moneymong.ledger.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.ledger.LedgerScreen

const val ledgerRoute = "ledger_route"

fun NavGraphBuilder.ledgerScreen(
    navigateToAgency: () -> Unit,
    navigateToOCR: (NavOptions?) -> Unit,
    navigateToLedgerDetail: (NavOptions?, Int) -> Unit,
    navigateToLedgerManual: (NavOptions?) -> Unit
) {
    composable(route = ledgerRoute) {
        LedgerScreen(
            navigateToAgency = navigateToAgency,
            navigateToOCR = navigateToOCR,
            navigateToLedgerDetail = navigateToLedgerDetail,
            navigateToLedgerManual = navigateToLedgerManual
        )
    }
}
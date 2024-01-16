package com.moneymong.moneymong.ledger.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.ledger.LedgerScreen

const val ledgerRoute = "ledger_route"

fun NavGraphBuilder.ledgerScreen() {
    composable(route = ledgerRoute) {
        LedgerScreen()
    }
}
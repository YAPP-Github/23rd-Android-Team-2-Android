package com.moneymong.moneymong.ledger.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.ledger.LedgerScreen

const val ledgerRoute = "ledger_route"

fun NavGraphBuilder.ledgerScreen(padding: PaddingValues) {
    composable(route = ledgerRoute) {
        LedgerScreen(modifier = Modifier.padding(padding))
    }
}
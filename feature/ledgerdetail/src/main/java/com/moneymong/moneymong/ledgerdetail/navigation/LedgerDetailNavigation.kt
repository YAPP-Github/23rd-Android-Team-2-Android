package com.moneymong.moneymong.ledgerdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moneymong.moneymong.ledgerdetail.LedgerDetailScreen

const val LEDGER_TRANSACTION_ID = "ledgerTransactionId"
const val ledgerDetailRoute = "ledgerDetail_route/{${LEDGER_TRANSACTION_ID}}"

fun NavController.navigateLedgerDetail(
    navOptions: NavOptions? = null,
    ledgerTransactionId: Int
) {
    this.navigate("ledgerDetail_route/${ledgerTransactionId}", navOptions)
}

fun NavGraphBuilder.ledgerDetailScreen(
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    popBackStack: () -> Unit
) {
    composable(
        route = ledgerDetailRoute,
        arguments = listOf(
            navArgument(LEDGER_TRANSACTION_ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val ledgerTransactionId = backStackEntry.arguments?.getInt(LEDGER_TRANSACTION_ID) ?: 0
        LedgerDetailScreen(
            ledgerTransactionId = ledgerTransactionId,
            navigateToLedger = navigateToLedger,
            popBackStack = popBackStack
        )
    }
}
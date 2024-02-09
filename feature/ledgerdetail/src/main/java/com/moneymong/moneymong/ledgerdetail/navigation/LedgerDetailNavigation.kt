package com.moneymong.moneymong.ledgerdetail.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moneymong.moneymong.ledgerdetail.LedgerDetailScreen

const val LEDGER_TRANSACTION_ID = "ledgerTransactionId"
const val LEDGER_DETAIL_IS_STAFF = "LedgerDetailIsStaff"
const val ledgerDetailRoute = "ledgerDetail_route/{${LEDGER_TRANSACTION_ID}}/{${LEDGER_DETAIL_IS_STAFF}}"
//const val ledgerDetailRouteWithArgs = "${ledgerDetailRoute}/{${LEDGER_DETAIL_IS_STAFF}}"

fun NavController.navigateLedgerDetail(
    navOptions: NavOptions? = null,
    ledgerTransactionId: Int,
    isStaff: Boolean = false,
) {
    this.navigate("ledgerDetail_route/${ledgerTransactionId}/${isStaff}", navOptions)
}

fun NavGraphBuilder.ledgerDetailScreen(
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    popBackStack: () -> Unit
) {
    composable(
        route = ledgerDetailRoute,
        arguments = listOf(
            navArgument(LEDGER_TRANSACTION_ID) { type = NavType.IntType },
            navArgument(LEDGER_DETAIL_IS_STAFF) {
                type = NavType.BoolType
                defaultValue = false
            }
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

internal class LedgerDetailArgs(val isStaff: Boolean) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        isStaff = checkNotNull(savedStateHandle[LEDGER_DETAIL_IS_STAFF])
    )
}
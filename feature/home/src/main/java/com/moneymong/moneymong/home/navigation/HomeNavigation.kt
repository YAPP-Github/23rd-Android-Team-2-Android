package com.moneymong.moneymong.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.moneymong.moneymong.home.HomeBottomTabs
import com.moneymong.moneymong.home.HomeScreen

const val HOME_LEDGER_POST_SUCCESS = "homeLedgerPostSuccess"
const val homeRoute = "home_route/{${HOME_LEDGER_POST_SUCCESS}}"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null,
    homeLedgerPostSuccess: Boolean = false
) {
    this.navigate("home_route/${homeLedgerPostSuccess}", navOptions)
}

fun NavController.topLevelNavigateToHome(homeLedgerPostSuccess: Boolean = false) {
    this.navigateToHome(
        navOptions = navOptions {
            popUpTo(
                graph.id
            ) { inclusive = true }
        },
        homeLedgerPostSuccess = homeLedgerPostSuccess
    )
}

fun NavGraphBuilder.homeScreen(
    navigateToOCR: (NavOptions?) -> Unit,
    navigateToLedgerDetail: (NavOptions?, Int) -> Unit,
    navigateToLedgerManual: (NavOptions?) -> Unit
) {
    composable(
        route = homeRoute,
        arguments = listOf(
            navArgument(HOME_LEDGER_POST_SUCCESS) { type = NavType.BoolType },
        )
    ) { backStackEntry ->
        val homeLedgerPostSuccess =
            backStackEntry.arguments?.getBoolean(HOME_LEDGER_POST_SUCCESS) ?: false
        HomeScreen(
            homeLedgerPostSuccess = homeLedgerPostSuccess,
            navigateToOCR = navigateToOCR,
            navigateToLedgerDetail = navigateToLedgerDetail,
            navigateToLedgerManual = navigateToLedgerManual
        )
    }
}

@Composable
internal fun rememberHomeNavigator(navHostController: NavHostController = rememberNavController()) =
    remember { HomeNavigator(navHostController = navHostController) }

internal class HomeNavigator(
    val navHostController: NavHostController
) {
    private val navBackStackEntry: NavBackStackEntry?
        @Composable get() = navHostController.currentBackStackEntryAsState().value
    val currentRoute: String
        @Composable get() = navBackStackEntry?.destination?.route ?: HomeBottomTabs.AGENCY.route
    val routes: List<String>
        @Composable get() = remember { HomeBottomTabs.values().map { it.route } }

    @Composable
    fun includeCurrentRouteInTabs() =
        navBackStackEntry?.destination?.route?.let {
            it in routes
        } ?: false

    fun navigate(route: String) {
        val navOptions = navOptions {
            popUpTo(navHostController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        navHostController.navigate(
            route = route,
            navOptions = navOptions
        )
    }
}
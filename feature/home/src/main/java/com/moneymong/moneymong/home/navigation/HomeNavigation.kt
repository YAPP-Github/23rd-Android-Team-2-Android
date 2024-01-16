package com.moneymong.moneymong.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable(
        route = homeRoute,
        arguments = listOf(
            navArgument(HOME_LEDGER_POST_SUCCESS) { type = NavType.BoolType },
        )
    ) { backStackEntry ->
        val homeLedgerPostSuccess = backStackEntry.arguments?.getBoolean(HOME_LEDGER_POST_SUCCESS) ?: false
        HomeScreen(
            navController = navController,
            homeLedgerPostSuccess = homeLedgerPostSuccess
        )
    }
}

@Composable
internal fun rememberHomeNavController(navHostController: NavHostController = rememberNavController()) =
    remember { HomeNavHostController(navHostController = navHostController) }

internal class HomeNavHostController(
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
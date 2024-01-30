package com.moneymong.moneymong.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moneymong.moneymong.home.HomeBottomTabs
import com.moneymong.moneymong.ledger.navigation.ledgerRoute
import com.moneymong.moneymong.ledger.navigation.ledgerRouteWithArgs

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
            popUpTo(ledgerRouteWithArgs) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        navHostController.navigate(
            route = if (route == ledgerRouteWithArgs) {
                "$ledgerRoute/${false}"
            } else {
                route
            },
            navOptions = navOptions
        )
    }
}
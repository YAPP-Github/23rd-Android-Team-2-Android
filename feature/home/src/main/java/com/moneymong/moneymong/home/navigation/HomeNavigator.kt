package com.moneymong.moneymong.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.navigation.agencyRegisterCompleteRoute
import com.moneymong.moneymong.feature.agency.navigation.agencyRoute
import com.moneymong.moneymong.feature.agency.navigation.navigateAgency
import com.moneymong.moneymong.feature.mymong.navigation.mymongRoute
import com.moneymong.moneymong.feature.mymong.navigation.navigateMyMong
import com.moneymong.moneymong.feature.sign.navigation.loginRoute
import com.moneymong.moneymong.feature.sign.navigation.splashRoute
import com.moneymong.moneymong.home.HomeBottomTabs
import com.moneymong.moneymong.ledger.navigation.ledgerRouteWithArgs
import com.moneymong.moneymong.ledger.navigation.navigateLedger
import com.moneymong.moneymong.ledgerdetail.navigation.ledgerDetailRoute

@Composable
internal fun rememberHomeNavigator(navHostController: NavHostController = rememberNavController()) =
    remember { HomeNavigator(navHostController = navHostController) }

internal class HomeNavigator(
    val navHostController: NavHostController
) {
    private val navBackStackEntry: NavBackStackEntry?
        @Composable get() = navHostController.currentBackStackEntryAsState().value

    private val routes: List<String>
        @Composable get() = remember { HomeBottomTabs.values().map { it.route } }

    val currentRoute: String?
        @Composable get() = navBackStackEntry?.destination?.route

    val statusBarColor: Color
        @Composable
        get() = when (currentRoute) {
            in listOf(
                splashRoute,
                loginRoute
            ) -> Blue04

            in listOf(
                agencyRoute,
                ledgerDetailRoute,
                mymongRoute
            ) -> Gray01

            agencyRegisterCompleteRoute -> Gray08

            else -> White
        }

    val darkIcons: Boolean
        @Composable
        get() = when (currentRoute) {
            in listOf(
                splashRoute,
                loginRoute,
                agencyRegisterCompleteRoute
            ) -> false

            else -> true
        }

    @Composable
    fun includeCurrentRouteInTabs() = currentRoute in routes

    fun navigate(route: String) {
        val navOptions = navOptions {
            popUpTo(ledgerRouteWithArgs) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (route) {
            HomeBottomTabs.AGENCY.route -> navHostController.navigateAgency(navOptions = navOptions)
            HomeBottomTabs.LEDGER.route -> navHostController.navigateLedger(navOptions = navOptions)
            HomeBottomTabs.MYMONG.route -> navHostController.navigateMyMong(navOptions = navOptions)
        }
    }
}
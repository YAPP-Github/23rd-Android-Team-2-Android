package com.moneymong.moneymong.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.moneymong.moneymong.feature.agency.join.navigation.agencyCompleteScreen
import com.moneymong.moneymong.feature.agency.join.navigation.agencyJoinScreen
import com.moneymong.moneymong.feature.agency.join.navigation.navigateAgencyJoin
import com.moneymong.moneymong.feature.agency.join.navigation.navigateAgencyJoinComplete
import com.moneymong.moneymong.feature.agency.navigation.agencyRegisterCompleteScreen
import com.moneymong.moneymong.feature.agency.navigation.agencyRegisterScreen
import com.moneymong.moneymong.feature.agency.navigation.agencyRoute
import com.moneymong.moneymong.feature.agency.navigation.agencyScreen
import com.moneymong.moneymong.feature.agency.navigation.navigateAgency
import com.moneymong.moneymong.feature.agency.navigation.navigateAgencyRegister
import com.moneymong.moneymong.feature.agency.navigation.navigateAgencyRegisterComplete
import com.moneymong.moneymong.feature.mymong.navigation.mymongScreen
import com.moneymong.moneymong.home.navigation.rememberHomeNavController
import com.moneymong.moneymong.home.view.HomeBottomBarView
import com.moneymong.moneymong.ledger.navigation.ledgerRoute
import com.moneymong.moneymong.ledger.navigation.ledgerScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val homeNavController = rememberHomeNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeBottomBarView(
                homeNavHostController = homeNavController,
                tabs = HomeBottomTabs.entries.toList()
            )
        }
    ) {
        NavHost(
            navController = homeNavController.navHostController,
            startDestination = agencyRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            agencyScreen(
                padding = it,
                navigateToRegister = { homeNavController.navHostController.navigateAgencyRegister() },
                navigateAgencyJoin = { agencyId ->
                    homeNavController.navHostController.navigateAgencyJoin(agencyId = agencyId)
                }
            )
            agencyJoinScreen(
                padding = it,
                navigateToComplete = { homeNavController.navHostController.navigateAgencyJoinComplete() },
                navigateUp = { homeNavController.navHostController.navigateUp() }
            )
            agencyCompleteScreen(
                padding = it,
                navController = homeNavController.navHostController,
                navigateToLedger = {
                    homeNavController.navHostController.navigate(
                        route = ledgerRoute,
                        navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateToJoin = {
                    homeNavController.navHostController.navigateAgency(
                        navOptions = navOptions {
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateUp = { homeNavController.navHostController.navigateUp() }
            )
            agencyRegisterScreen(
                padding = it,
                navigateToComplete = { homeNavController.navHostController.navigateAgencyRegisterComplete() },
                navigateUp = { homeNavController.navHostController.navigateUp() }
            )
            agencyRegisterCompleteScreen(
                padding = it,
                navigateToSearch = {
                    homeNavController.navHostController.navigateAgency(
                        navOptions = navOptions {
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateToLedger = {
                    homeNavController.navHostController.navigate(
                        route = ledgerRoute,
                        navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                }
            )

            ledgerScreen(padding = it)

            mymongScreen(padding = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
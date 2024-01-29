package com.moneymong.moneymong.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
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
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.feature.mymong.navigation.myMongNavGraph
import com.moneymong.moneymong.feature.mymong.navigation.navigatePrivacyPolicy
import com.moneymong.moneymong.feature.mymong.navigation.navigateTermsOfUse
import com.moneymong.moneymong.feature.mymong.navigation.navigateWithdrawal
import com.moneymong.moneymong.home.navigation.rememberHomeNavigator
import com.moneymong.moneymong.home.view.HomeBottomBarView
import com.moneymong.moneymong.ledger.navigation.ledgerRoute
import com.moneymong.moneymong.ledger.navigation.ledgerScreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeLedgerPostSuccess: Boolean,
    navigateToOCR: (NavOptions?) -> Unit,
    navigateToLedgerDetail: (NavOptions?, Int) -> Unit,
    navigateToLedgerManual: (NavOptions?) -> Unit
) {
    val homeNavigator = rememberHomeNavigator()
    val homeNavController = homeNavigator.navHostController
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        if (homeLedgerPostSuccess) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "성공적으로 기록됐습니다",
                    withDismissAction = true,
                    actionLabel = ""
                )
            }
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeBottomBarView(
                homeNavigator = homeNavigator,
                tabs = HomeBottomTabs.entries.toList()
            )
        },
        snackbarHost = {
            MDSSnackbarHost(
                modifier = Modifier.padding(start = 20.dp, bottom = 12.dp, end = 20.dp),
                hostState = snackbarHostState
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = homeNavController,
            startDestination = ledgerRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            agencyScreen(
                navigateToRegister = { homeNavController.navigateAgencyRegister() },
                navigateAgencyJoin = { agencyId ->
                    homeNavController.navigateAgencyJoin(agencyId = agencyId)
                }
            )
            
            agencyJoinScreen(
                navigateToComplete = { homeNavController.navigateAgencyJoinComplete() },
                navigateUp = { homeNavController.navigateUp() }
            )
            
            agencyCompleteScreen(
                navigateToLedger = {
                    homeNavController.navigate(
                        route = ledgerRoute,
                        navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateToJoin = {
                    homeNavController.navigateAgency(
                        navOptions = navOptions {
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
            )
            
            agencyRegisterScreen(
                navigateToComplete = { homeNavController.navigateAgencyRegisterComplete() },
                navigateUp = { homeNavController.navigateUp() }
            )
            
            agencyRegisterCompleteScreen(
                navigateToSearch = {
                    homeNavController.navigateAgency(
                        navOptions = navOptions {
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateToLedger = {
                    homeNavController.navigate(
                        route = ledgerRoute,
                        navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                }
            )
            
            ledgerScreen(
                navigateToAgency = { homeNavController.navigate(agencyRoute) },
                navigateToOCR = navigateToOCR,
                navigateToLedgerDetail = navigateToLedgerDetail,
                navigateToLedgerManual = navigateToLedgerManual
            )

            myMongNavGraph(
                navigateToTermsOfUse = homeNavController::navigateTermsOfUse,
                navigateToPrivacyPolicy = homeNavController::navigatePrivacyPolicy,
                navigateToWithdrawal = homeNavController::navigateWithdrawal,
                navigateToLogin = { /* todo navigateToLogin */ },
                navigateUp = homeNavController::navigateUp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeLedgerPostSuccess = false,
        navigateToOCR = {},
        navigateToLedgerDetail = { navOptions, i ->  },
        navigateToLedgerManual = {}
    )
}
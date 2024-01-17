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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.feature.agency.navigation.agencyRoute
import com.moneymong.moneymong.feature.agency.navigation.agencyScreen
import com.moneymong.moneymong.feature.mymong.navigation.mymongScreen
import com.moneymong.moneymong.home.navigation.rememberHomeNavController
import com.moneymong.moneymong.home.view.HomeBottomBarView
import com.moneymong.moneymong.ledger.navigation.ledgerRoute
import com.moneymong.moneymong.ledger.navigation.ledgerScreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeLedgerPostSuccess: Boolean
) {
    val homeNavController = rememberHomeNavController()
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
                homeNavHostController = homeNavController,
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
            navController = homeNavController.navHostController,
            startDestination = ledgerRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            agencyScreen(padding = it)

            ledgerScreen(
                padding = it,
                navigateToAgency = { homeNavController.navigate(agencyRoute) }
            )

            mymongScreen(padding = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        homeLedgerPostSuccess = false
    )
}
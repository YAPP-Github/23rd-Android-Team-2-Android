package com.moneymong.moneymong.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.moneymong.moneymong.feature.agency.navigation.agencyRoute
import com.moneymong.moneymong.feature.agency.navigation.agencyScreen
import com.moneymong.moneymong.feature.mymong.navigation.myMongNavGraph
import com.moneymong.moneymong.feature.mymong.navigation.navigatePrivacyPolicy
import com.moneymong.moneymong.feature.mymong.navigation.navigateTermsOfUse
import com.moneymong.moneymong.home.navigation.rememberHomeNavigator
import com.moneymong.moneymong.home.view.HomeBottomBarView
import com.moneymong.moneymong.ledger.navigation.ledgerScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val homeNavigator = rememberHomeNavigator()
    val homeNavController = homeNavigator.navHostController

    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeBottomBarView(
                homeNavigator = homeNavigator,
                tabs = HomeBottomTabs.entries.toList()
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = homeNavController,
            startDestination = agencyRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            agencyScreen()

            ledgerScreen()

            myMongNavGraph(
                navigateToTermsOfUse = homeNavController::navigateTermsOfUse,
                navigateToPrivacyPolicy = homeNavController::navigatePrivacyPolicy,
                navigateUp = homeNavController::navigateUp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
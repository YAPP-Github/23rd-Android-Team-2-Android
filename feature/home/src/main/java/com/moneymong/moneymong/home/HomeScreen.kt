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
import com.moneymong.moneymong.feature.mymong.navigation.mymongScreen
import com.moneymong.moneymong.feature.mymong.navigation.navigatePrivacyPolicy
import com.moneymong.moneymong.feature.mymong.navigation.navigateTermsOfUse
import com.moneymong.moneymong.feature.mymong.navigation.privacyPolicyScreen
import com.moneymong.moneymong.feature.mymong.navigation.termsOfUseScreen
import com.moneymong.moneymong.home.navigation.rememberHomeNavController
import com.moneymong.moneymong.home.view.HomeBottomBarView
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
            modifier = Modifier.padding(it),
            navController = homeNavController.navHostController,
            startDestination = agencyRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            agencyScreen()

            ledgerScreen()

            mymongScreen(
                navigateToTermsOfUse = homeNavController.navHostController::navigateTermsOfUse,
                navigateToPrivacyPolicy = homeNavController.navHostController::navigatePrivacyPolicy
            )
            termsOfUseScreen(navigateUp = homeNavController.navHostController::navigateUp)
            privacyPolicyScreen(navigateUp = homeNavController.navHostController::navigateUp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
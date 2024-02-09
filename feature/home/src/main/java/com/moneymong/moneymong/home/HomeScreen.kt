package com.moneymong.moneymong.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
import com.moneymong.moneymong.feature.mymong.navigation.myMongNavGraph
import com.moneymong.moneymong.feature.mymong.navigation.navigatePrivacyPolicy
import com.moneymong.moneymong.feature.mymong.navigation.navigateTermsOfUse
import com.moneymong.moneymong.feature.mymong.navigation.navigateWithdrawal
import com.moneymong.moneymong.feature.sign.navigation.loginScreen
import com.moneymong.moneymong.feature.sign.navigation.navigateLogin
import com.moneymong.moneymong.feature.sign.navigation.navigateSignComplete
import com.moneymong.moneymong.feature.sign.navigation.navigateSignUp
import com.moneymong.moneymong.feature.sign.navigation.signCompleteScreen
import com.moneymong.moneymong.feature.sign.navigation.signUpScreen
import com.moneymong.moneymong.feature.sign.navigation.splashRoute
import com.moneymong.moneymong.feature.sign.navigation.splashScreen
import com.moneymong.moneymong.home.navigation.rememberHomeNavigator
import com.moneymong.moneymong.home.view.HomeBottomBarView
import com.moneymong.moneymong.ledger.navigation.ledgerScreen
import com.moneymong.moneymong.ledger.navigation.navigateLedger
import com.moneymong.moneymong.ledgerdetail.navigation.ledgerDetailScreen
import com.moneymong.moneymong.ledgerdetail.navigation.navigateLedgerDetail
import com.moneymong.moneymong.ledgermanual.navigation.ledgerManualScreen
import com.moneymong.moneymong.ledgermanual.navigation.navigateLedgerManual
import com.moneymong.moneymong.ocr.navigation.navigateOCR
import com.moneymong.moneymong.ocr.navigation.ocrScreen
import com.moneymong.moneymong.ocr_detail.navigation.navigateOCRDetail
import com.moneymong.moneymong.ocr_detail.navigation.ocrDetailScreen
import com.moneymong.moneymong.ocr_result.navigation.navigateOCRResult
import com.moneymong.moneymong.ocr_result.navigation.ocrResultScreen

@Composable
fun HomeScreen(
    expired: Boolean,
    onChangeExpired: (Boolean) -> Unit
) {
    val homeNavigator = rememberHomeNavigator()
    val homeNavController = homeNavigator.navHostController

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = homeNavigator.statusBarColor,
        darkIcons = homeNavigator.darkIcons
    )

    LaunchedEffect(expired) {
        if (expired) run {
            homeNavController.navigateLogin()
            onChangeExpired(false)
        }
    }

    Scaffold(
        bottomBar = {
            HomeBottomBarView(
                visible = homeNavigator.includeCurrentRouteInTabs(),
                tabs = HomeBottomTabs.entries.toList(),
                currentRoute = homeNavigator.currentRoute,
                navigateToTab = { homeNavigator.navigate(it.route) }
            )
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = homeNavController,
            startDestination = splashRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            splashScreen(
                navigateToLedger = homeNavController::navigateLedger,
                navigateToLogin = homeNavController::navigateLogin
            )

            // sign
            loginScreen(
                navigateToLedger = homeNavController::navigateLedger,
                navigateToSignUp = homeNavController::navigateSignUp,
                navigateToLogin = homeNavController::navigateLogin,
            )

            signUpScreen(
                navigateToSignComplete = homeNavController::navigateSignComplete,
                navigateUp = homeNavController::navigateUp
            )

            signCompleteScreen(
                navigateToLedger = homeNavController::navigateLedger
            )

            // agency
            agencyScreen(
                padding = padding,
                navigateToRegister = homeNavController::navigateAgencyRegister,
                navigateAgencyJoin = homeNavController::navigateAgencyJoin
            )

            agencyJoinScreen(
                navigateToComplete = homeNavController::navigateAgencyJoinComplete,
                navigateUp = homeNavController::navigateUp
            )

            agencyCompleteScreen(
                navigateToSearch = {
                    homeNavController.navigateAgency(
                        navOptions = navOptions {
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateToLedger = homeNavController::navigateLedger,
            )

            agencyRegisterScreen(
                navigateToComplete = homeNavController::navigateAgencyRegisterComplete,
                navigateUp = homeNavController::navigateUp
            )

            agencyRegisterCompleteScreen(
                navigateToSearch = {
                    homeNavController.navigateAgency(
                        navOptions = navOptions {
                            popUpTo(agencyRoute) { inclusive = true }
                        }
                    )
                },
                navigateToLedger = homeNavController::navigateLedger,
                navigateToLedgerManual = homeNavController::navigateLedgerManual
            )

            // ledger
            ledgerScreen(
                padding = padding,
                navigateToAgency = { homeNavigator.navigate(agencyRoute) },
                navigateToOCR = homeNavController::navigateOCR,
                navigateToLedgerDetail = homeNavController::navigateLedgerDetail,
                navigateToLedgerManual = homeNavController::navigateLedgerManual
            )

            ledgerDetailScreen(
                navigateToLedger = homeNavController::navigateLedger,
                popBackStack = homeNavController::popBackStack
            )

            ledgerManualScreen(
                navigateToLedger = homeNavController::navigateLedger,
                popBackStack = homeNavController::popBackStack
            )

            // ocr
            ocrScreen(
                navigateToOCRResult = homeNavController::navigateOCRResult,
                navigateToLedger = homeNavController::navigateLedger,
                popBackStack = homeNavController::popBackStack
            )

            ocrResultScreen(
                navigateToLedger = homeNavController::navigateLedger,
                navigateToOCRDetail = homeNavController::navigateOCRDetail,
                popBackStack = homeNavController::popBackStack
            )

            ocrDetailScreen(
                navigateToLedger = homeNavController::navigateLedger,
                popBackStack = homeNavController::popBackStack
            )

            // mymong
            myMongNavGraph(
                padding = padding,
                navigateToTermsOfUse = homeNavController::navigateTermsOfUse,
                navigateToPrivacyPolicy = homeNavController::navigatePrivacyPolicy,
                navigateToWithdrawal = homeNavController::navigateWithdrawal,
                navigateToLogin = homeNavController::navigateLogin,
                navigateUp = homeNavController::navigateUp
            )
        }
    }
}
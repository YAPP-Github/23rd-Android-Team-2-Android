package com.moneymong.moneymong.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.moneymong.moneymong.ledgerdetail.navigation.navigateToLedgerDetail
import com.moneymong.moneymong.ledgermanual.navigation.ledgerManualScreen
import com.moneymong.moneymong.ledgermanual.navigation.navigateToLedgerManual
import com.moneymong.moneymong.ocr.navigation.navigateToOCR
import com.moneymong.moneymong.ocr.navigation.ocrScreen
import com.moneymong.moneymong.ocr_detail.navigation.navigateToOCRDetail
import com.moneymong.moneymong.ocr_detail.navigation.ocrDetailScreen
import com.moneymong.moneymong.ocr_result.navigation.navigateToOCRResult
import com.moneymong.moneymong.ocr_result.navigation.ocrResultScreen

@Composable
fun HomeScreen() {
    val homeNavigator = rememberHomeNavigator()
    val homeNavController = homeNavigator.navHostController

    Scaffold(
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
                navigateToLedger = homeNavController::navigateLedger
            )

            // ledger
            ledgerScreen(
                navigateToAgency = { homeNavController.navigate(agencyRoute) },
                navigateToOCR = homeNavController::navigateToOCR,
                navigateToLedgerDetail = homeNavController::navigateToLedgerDetail,
                navigateToLedgerManual = homeNavController::navigateToLedgerManual
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
                navigateToOCRResult = homeNavController::navigateToOCRResult,
                navigateToLedger = homeNavController::navigateLedger,
                popBackStack = homeNavController::popBackStack
            )

            ocrResultScreen(
                navigateToLedger = homeNavController::navigateLedger,
                navigateToOCRDetail = homeNavController::navigateToOCRDetail,
                popBackStack = homeNavController::popBackStack
            )

            ocrDetailScreen(
                navigateToLedger = homeNavController::navigateLedger,
                popBackStack = homeNavController::popBackStack
            )

            // mymong
            myMongNavGraph(
                navigateToTermsOfUse = homeNavController::navigateTermsOfUse,
                navigateToPrivacyPolicy = homeNavController::navigatePrivacyPolicy,
                navigateToWithdrawal = homeNavController::navigateWithdrawal,
                navigateToLogin = homeNavController::navigateLogin,
                navigateUp = homeNavController::navigateUp
            )
        }
    }
}
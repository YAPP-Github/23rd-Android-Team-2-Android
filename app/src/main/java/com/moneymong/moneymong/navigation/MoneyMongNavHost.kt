package com.moneymong.moneymong.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.moneymong.moneymong.feature.sign.navigation.loginRoute
import com.moneymong.moneymong.feature.sign.navigation.loginScreen
import com.moneymong.moneymong.feature.sign.navigation.signCompleteRoute
import com.moneymong.moneymong.feature.sign.navigation.signCompleteScreen
import com.moneymong.moneymong.feature.sign.navigation.signUpRoute
import com.moneymong.moneymong.feature.sign.navigation.signUpScreen
import com.moneymong.moneymong.feature.sign.navigation.splashRoute
import com.moneymong.moneymong.feature.sign.navigation.splashScreen
import com.moneymong.moneymong.home.navigation.homeRoute
import com.moneymong.moneymong.home.navigation.homeScreen
import com.moneymong.moneymong.home.navigation.navigateToHome
import com.moneymong.moneymong.home.navigation.topLevelNavigateToHome
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
fun MoneyMongNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = splashRoute

) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
       splashScreen(
            navigateToHome = {
                navController.navigate(
                    route = homeRoute,
                    navOptions = navOptions {
                        popUpTo(splashRoute) { inclusive = true }

                    }
                )
            },

            navigateToLogin = {
                navController.navigate(
                    route = loginRoute,
                    navOptions = navOptions {
                        popUpTo(splashRoute) { inclusive = true }
                    }
                )
            }
        )
        
        loginScreen(
            navigateToHome = {
                navController.navigate(
                    route = homeRoute,
                    navOptions = navOptions {
                        popUpTo(loginRoute) {inclusive = true }
                    }
                )
            },
            navigateToSignUp = {
                navController.navigate(
                    route = signUpRoute
                )
            },
            navigateToLogin = {
                navController.navigate(
                    route = loginRoute
                )
            }
        )
        
        signUpScreen(
            navigateToSignComplete = {
                navController.navigate(
                    route = signCompleteRoute,
                    navOptions = navOptions {
                        popUpTo(signUpRoute) { inclusive = true }
                    }
                )
            },
            navigateUp = {
                navController.navigateUp()
            }
        )
        
        signCompleteScreen(
            navigateToHome = {
                navController.navigate(
                    route = homeRoute,
                    navOptions = navOptions {
                        popUpTo(signCompleteRoute) { inclusive = true }
                    }
                )
            }
        )
      
        homeScreen(
            navigateToOCR = navController::navigateToOCR,
            navigateToLedgerDetail = navController::navigateToLedgerDetail,
            navigateToLedgerManual = navController::navigateToLedgerManual
        )
        
        ocrScreen(
            navigateToOCRResult = navController::navigateToOCRResult,
            navigateToHome = navController::topLevelNavigateToHome,
            popBackStack = navController::popBackStack
        )
        
        ocrResultScreen(
            navigateToHome = navController::topLevelNavigateToHome,
            navigateToOCRDetail = navController::navigateToOCRDetail,
            popBackStack = navController::popBackStack
        )
        
        ocrDetailScreen(
            navigateToHome = navController::topLevelNavigateToHome,
            popBackStack = navController::popBackStack
        )
        
        ledgerDetailScreen(
            navigateToHome = navController::topLevelNavigateToHome,
            popBackStack = navController::popBackStack
        )
        
        ledgerManualScreen(
            popBackStack = navController::popBackStack,
            navigateToHome = navController::topLevelNavigateToHome
        )
    }
}
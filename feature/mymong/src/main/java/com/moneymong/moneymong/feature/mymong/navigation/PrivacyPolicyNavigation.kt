package com.moneymong.moneymong.feature.mymong.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.privacypolicy.PrivacyPolicyScreen


const val privacyPolicyRoute = "privacyPolicy_route"

fun NavController.navigatePrivacyPolicy(navOptions: NavOptions? = null) {
    navigate(privacyPolicyRoute, navOptions)
}

internal fun NavGraphBuilder.privacyPolicyScreen(navigateUp: () -> Unit) {
    composable(route = privacyPolicyRoute) {
        PrivacyPolicyScreen(navigateUp = navigateUp)
    }
}
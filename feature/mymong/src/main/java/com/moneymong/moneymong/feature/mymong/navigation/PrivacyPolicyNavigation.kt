package com.moneymong.moneymong.feature.mymong.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.mymong.privacypolicy.PrivacyPolicyScreen


const val privacyPolicyRoute = "privacyPolicy_route"

fun NavController.navigatePrivacyPolicy(navOptions: NavOptions? = null) {
    navigate(privacyPolicyRoute, navOptions)
}

fun NavGraphBuilder.privacyPolicyScreen(
    padding: PaddingValues,
    navigateUp: () -> Unit
) {
    composable(route = privacyPolicyRoute) {
        PrivacyPolicyScreen(
            modifier = Modifier.padding(padding),
            navigateUp = navigateUp
        )
    }
}
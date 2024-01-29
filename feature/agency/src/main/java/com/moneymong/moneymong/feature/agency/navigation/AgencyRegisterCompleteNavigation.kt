package com.moneymong.moneymong.feature.agency.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.register.complete.AgencyRegisterCompleteScreen

const val agencyRegisterCompleteRoute = "agencyRegisterComplete_route"

fun NavController.navigateAgencyRegisterComplete(navOptions: NavOptions? = null) {
    navigate(agencyRegisterCompleteRoute, navOptions)
}

fun NavGraphBuilder.agencyRegisterCompleteScreen(
    navigateToSearch: () -> Unit,
    navigateToLedger: () -> Unit
) {
    composable(route = agencyRegisterCompleteRoute) {
        AgencyRegisterCompleteScreen(
            navigateToSearch = navigateToSearch,
            navigateToLedger = navigateToLedger
        )
    }
}
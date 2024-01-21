package com.moneymong.moneymong.feature.agency.join.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.join.AgencyCompleteScreen

const val agencyCompleteRoute = "agency_complete_route"

fun NavController.navigateAgencyJoinComplete(navOptions: NavOptions? = null) {
    navigate(agencyCompleteRoute, navOptions)
}

fun NavGraphBuilder.agencyCompleteScreen(
    padding: PaddingValues,
    navController: NavHostController,
    navigateToLedger : () -> Unit,
    navigateToJoin : () -> Unit,
    navigateUp : () -> Unit
) {
    composable(route = agencyCompleteRoute) {
        AgencyCompleteScreen(
            modifier = Modifier.padding(padding),
            navController = navController,
            navigateToLedger = navigateToLedger,
            navigateToJoin = navigateToJoin,
            navigateUp = navigateUp
            )
    }
}
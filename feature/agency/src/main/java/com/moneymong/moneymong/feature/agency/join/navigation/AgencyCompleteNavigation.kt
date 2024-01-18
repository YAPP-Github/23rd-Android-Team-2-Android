package com.moneymong.moneymong.feature.agency.join.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.join.AgencyCompleteScreen
import com.moneymong.moneymong.feature.agency.join.AgencyJoinScreen

const val agencyCompleteRoute = "agency_complete_route"

fun NavGraphBuilder.agencyCompleteScreen(navController: NavHostController) {
    composable(route = agencyCompleteRoute) {
        AgencyCompleteScreen(navController = navController )
    }
}
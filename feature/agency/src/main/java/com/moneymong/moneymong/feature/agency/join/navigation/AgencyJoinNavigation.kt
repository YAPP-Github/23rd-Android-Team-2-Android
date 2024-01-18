package com.moneymong.moneymong.feature.agency.join.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.join.AgencyJoinScreen

const val agencyJoinRoute = "agency_join_route"

fun NavGraphBuilder.agencyJoinScreen(navController: NavHostController) {
    composable(route = agencyJoinRoute) {
        AgencyJoinScreen(navController = navController )
    }
}
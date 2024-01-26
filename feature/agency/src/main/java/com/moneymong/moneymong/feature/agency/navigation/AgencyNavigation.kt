package com.moneymong.moneymong.feature.agency.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.search.AgencySearchScreen

const val agencyRoute = "agency_route"

fun NavGraphBuilder.agencyScreen() {
    composable(route = agencyRoute) {
        AgencySearchScreen()
    }
}
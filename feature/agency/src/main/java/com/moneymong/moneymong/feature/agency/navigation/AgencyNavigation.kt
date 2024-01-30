package com.moneymong.moneymong.feature.agency.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.moneymong.moneymong.feature.agency.search.AgencySearchScreen

const val agencyRoute = "agency_route"

fun NavController.navigateAgency(
    navOptions: NavOptions? = navOptions {
        popUpTo(graph.id) { inclusive = true }
    }
) {
    navigate(agencyRoute, navOptions)
}

fun NavGraphBuilder.agencyScreen(
    navigateToRegister: () -> Unit,
    navigateAgencyJoin: (agencyId: Long) -> Unit
) {
    composable(route = agencyRoute) {
        AgencySearchScreen(
            navigateToRegister = navigateToRegister,
            navigateAgencyJoin = navigateAgencyJoin
        )
    }
}
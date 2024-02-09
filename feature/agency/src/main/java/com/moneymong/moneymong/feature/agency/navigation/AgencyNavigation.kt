package com.moneymong.moneymong.feature.agency.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.search.AgencySearchScreen

const val agencyRoute = "agency_route"

fun NavController.navigateAgency(
    navOptions: NavOptions? = null
) {
    navigate(agencyRoute, navOptions)
}

fun NavGraphBuilder.agencyScreen(
    padding: PaddingValues,
    navigateToRegister: () -> Unit,
    navigateAgencyJoin: (agencyId: Long) -> Unit
) {
    composable(route = agencyRoute) {
        AgencySearchScreen(
            modifier = Modifier.padding(padding),
            navigateToRegister = navigateToRegister,
            navigateAgencyJoin = navigateAgencyJoin
        )
    }
}
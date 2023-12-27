package com.moneymong.moneymong.feature.agency.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.search.AgencySearchScreen

const val agencyRoute = "agency_route"

fun NavGraphBuilder.agencyScreen(padding: PaddingValues) {
    composable(route = agencyRoute) {
        AgencySearchScreen(modifier = Modifier.padding(padding))
    }
}
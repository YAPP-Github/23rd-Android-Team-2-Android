package com.moneymong.moneymong.feature.agency.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.moneymong.moneymong.feature.agency.register.AgencyRegisterScreen

const val agencyRegisterRoute = "agencyRegister_route"

fun NavController.navigateAgencyRegister(navOptions: NavOptions? = null) {
    navigate(agencyRegisterRoute, navOptions)
}

fun NavGraphBuilder.agencyRegisterScreen(
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit
) {
    composable(route = agencyRegisterRoute) {
        AgencyRegisterScreen(
            navigateToComplete = navigateToComplete,
            navigateUp = navigateUp
        )
    }
}
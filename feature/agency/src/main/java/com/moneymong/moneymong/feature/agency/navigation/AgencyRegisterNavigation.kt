package com.moneymong.moneymong.feature.agency.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
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
    padding: PaddingValues,
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit
) {
    composable(route = agencyRegisterRoute) {
        AgencyRegisterScreen(
            modifier = Modifier.padding(padding),
            navigateToComplete = navigateToComplete,
            navigateUp = navigateUp
        )
    }
}
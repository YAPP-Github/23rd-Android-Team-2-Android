package com.moneymong.moneymong.feature.agency.join.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moneymong.moneymong.feature.agency.join.AgencyJoinScreen

const val agencyIdArg = "agencyId"
const val agencyJoinRoute = "agencyJoin_route"
const val routeWithArgs = "${agencyJoinRoute}/{${agencyIdArg}}"

private val arguments = listOf(
    navArgument(agencyIdArg) {
        type = NavType.LongType
    }
)

fun NavController.navigateAgencyJoin(agencyId: Long, navOptions: NavOptions? = null) {
    navigate("${agencyJoinRoute}/$agencyId", navOptions)
}

fun NavGraphBuilder.agencyJoinScreen(
    navigateToComplete: () -> Unit,
    navigateUp: () -> Unit,
) {
    composable(
        route = routeWithArgs,
        arguments = arguments
    ) {
        AgencyJoinScreen(
            navigateToComplete = navigateToComplete,
            navigateUp = navigateUp,
            agencyId = it.arguments?.getLong(agencyIdArg) ?: 0
        )
    }
}
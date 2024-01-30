package com.moneymong.moneymong.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.moneymong.moneymong.feature.agency.navigation.agencyRoute
import com.moneymong.moneymong.feature.mymong.navigation.mymongRoute
import com.moneymong.moneymong.ledger.navigation.ledgerRouteWithArgs

enum class HomeBottomTabs(
    @StringRes val labelText: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    AGENCY(
        labelText = R.string.home_bottom_tabs_label_agency,
        icon = com.moneymong.moneymong.design_system.R.drawable.ic_party,
        route = agencyRoute
    ),
    LEDGER(
        labelText = R.string.home_bottom_tabs_label_ledger,
        icon = com.moneymong.moneymong.design_system.R.drawable.ic_record,
        route = ledgerRouteWithArgs
    ),
    MYMONG(
        labelText = R.string.home_bottom_tabs_label_mymong,
        icon = com.moneymong.moneymong.design_system.R.drawable.ic_mymong,
        route = mymongRoute
    )
}
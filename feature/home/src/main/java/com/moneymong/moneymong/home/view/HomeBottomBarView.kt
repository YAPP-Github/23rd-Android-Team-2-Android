package com.moneymong.moneymong.home.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moneymong.moneymong.design_system.component.navigation.MDSNavigationBar
import com.moneymong.moneymong.design_system.component.navigation.MDSNavigationBarItem
import com.moneymong.moneymong.home.HomeBottomTabs
import com.moneymong.moneymong.home.navigation.HomeNavHostController

@Composable
internal fun HomeBottomBarView(
    modifier: Modifier = Modifier,
    homeNavHostController: HomeNavHostController,
    tabs: List<HomeBottomTabs>
) {
    if (homeNavHostController.includeCurrentRouteInTabs()) {
        MDSNavigationBar(modifier = modifier) {
            tabs.forEach { tab ->
                MDSNavigationBarItem(
                    selected = homeNavHostController.currentRoute == tab.route,
                    labelText = stringResource(id = tab.labelText),
                    icon = tab.icon,
                    onClick = { homeNavHostController.navigate(tab.route) }
                )
            }
        }
    }
}
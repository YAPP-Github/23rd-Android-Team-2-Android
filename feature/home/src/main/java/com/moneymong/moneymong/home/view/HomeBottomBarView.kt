package com.moneymong.moneymong.home.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moneymong.moneymong.design_system.component.navigation.MDSNavigationBar
import com.moneymong.moneymong.design_system.component.navigation.MDSNavigationBarItem
import com.moneymong.moneymong.home.HomeBottomTabs
import com.moneymong.moneymong.home.navigation.HomeNavigator

@Composable
internal fun HomeBottomBarView(
    modifier: Modifier = Modifier,
    homeNavigator: HomeNavigator,
    tabs: List<HomeBottomTabs>
) {
    if (homeNavigator.includeCurrentRouteInTabs()) {
        MDSNavigationBar(modifier = modifier) {
            tabs.forEach { tab ->
                MDSNavigationBarItem(
                    selected = homeNavigator.currentRoute == tab.route,
                    labelText = stringResource(id = tab.labelText),
                    icon = tab.icon,
                    onClick = { homeNavigator.navigate(tab.route) }
                )
            }
        }
    }
}
package com.moneymong.moneymong.home.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.moneymong.moneymong.design_system.component.navigation.MDSNavigationBar
import com.moneymong.moneymong.design_system.component.navigation.MDSNavigationBarItem
import com.moneymong.moneymong.home.HomeBottomTabs

@Composable
internal fun HomeBottomBarView(
    modifier: Modifier = Modifier,
    visible: Boolean,
    tabs: List<HomeBottomTabs>,
    currentRoute: String?,
    navigateToTab: (HomeBottomTabs) -> Unit
) {
    if (visible) {
        MDSNavigationBar(modifier = modifier) {
            tabs.forEach { tab ->
                MDSNavigationBarItem(
                    selected = tab.route == currentRoute,
                    labelText = stringResource(id = tab.labelText),
                    icon = tab.icon,
                    onClick = { navigateToTab(tab) }
                )
            }
        }
    }
}
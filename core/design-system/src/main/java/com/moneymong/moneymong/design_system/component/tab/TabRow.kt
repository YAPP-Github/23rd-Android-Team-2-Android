package com.moneymong.moneymong.design_system.component.tab

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MDSTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTabIndex: Int,
    onChangeSelectedTabIndex: (Int) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        containerColor = White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = MDSTabType.Selected.indicatorColor
            )
        },
        divider = {
            Divider(color = MDSTabType.UnSelected.indicatorColor)
        }
    ) {
        tabs.forEachIndexed { index, title ->
            val selected = selectedTabIndex == index
            Tab(
                selected = selected,
                onClick = { onChangeSelectedTabIndex(index) },
                selectedContentColor = MDSTabType.Selected.contentColor,
                unselectedContentColor = MDSTabType.UnSelected.contentColor,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = title,
                    style = Body3
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MDSTabRowPreview() {
    MDSTabRow(
        tabs = listOf("tab", "tab", "tab"),
        selectedTabIndex = 0,
        onChangeSelectedTabIndex = { }
    )
}
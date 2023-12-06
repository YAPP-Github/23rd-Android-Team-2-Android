package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray10
import kotlinx.coroutines.launch

enum class LedgerTab(val title: String) {
    Ledger("장부"),
    Member("멤버")
}

@Composable
fun LedgerTabRowView(
    modifier: Modifier = Modifier,
    tabs: List<LedgerTab> = listOf(LedgerTab.Ledger, LedgerTab.Member),
    selectedTabIndex: MutableState<Int>,
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex.value,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart)
                    .offset(x = tabPositions[selectedTabIndex.value].left)
                    .width(tabPositions[selectedTabIndex.value].width),
                color = Blue04
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = {
                    coroutineScope.launch {
                        selectedTabIndex.value = index
                    }
                },
                text = {
                    Text(
                        text = tab.title,
                        style = Body3,
                        color = Gray10
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerTabRowPreview() {
    LedgerTabRowView(selectedTabIndex = remember {
        mutableIntStateOf(0)
    })
}
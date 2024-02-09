package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moneymong.moneymong.design_system.component.tab.MDSTabRow
import com.moneymong.moneymong.design_system.theme.White

enum class LedgerTab(val title: String) {
    Ledger("장부"),
    Member("멤버")
}

@Composable
fun LedgerTabRowView(
    tabs: List<LedgerTab>,
    selectedTabIndex: Int,
    onScrollToPage: (Int) -> Unit
) {
    MDSTabRow(
        modifier = Modifier.background(White),
        tabs = tabs.map { it.title },
        selectedTabIndex = selectedTabIndex,
        onChangeSelectedTabIndex = {
            onScrollToPage(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LedgerTabRowPreview() {
    LedgerTabRowView(
        tabs = listOf(LedgerTab.Ledger, LedgerTab.Member),
        selectedTabIndex = 0,
        onScrollToPage = {}
    )
}
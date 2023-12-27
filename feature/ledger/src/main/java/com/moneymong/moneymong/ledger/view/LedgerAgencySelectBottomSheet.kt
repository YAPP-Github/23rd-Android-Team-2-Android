package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.ledger.view.item.LedgerAgencySelectItem

@Composable
fun LedgerAgencySelectBottomSheet(
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit
) {
    val list = listOf(0, 1)
    Column(
        modifier = modifier.padding(vertical = 24.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        list.forEachIndexed { index, item -> // TODO
            LedgerAgencySelectItem(onClick = onClickItem)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerAgencySelectBottomSheetPreview() {
    LedgerAgencySelectBottomSheet() {}
}
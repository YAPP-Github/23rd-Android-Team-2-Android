package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.ledger.view.item.LedgerGroupSelectItem

@Composable
fun LedgerGroupSelectBottomSheet(
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit
) {
    val list = listOf(0, 1)
    Column(
        modifier = modifier.padding(vertical = 24.dp, horizontal = 20.dp)
    ) {
        list.forEachIndexed { index, item -> // TODO
            LedgerGroupSelectItem(onClick = onClickItem)
            if (index != list.lastIndex) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerGroupSelectBottomSheetPreview() {
    LedgerGroupSelectBottomSheet() {}
}
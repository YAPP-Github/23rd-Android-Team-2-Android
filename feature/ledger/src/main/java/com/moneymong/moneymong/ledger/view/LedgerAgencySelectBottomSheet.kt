package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.domain.entity.agency.MyAgencyEntity
import com.moneymong.moneymong.ledger.view.item.LedgerAgencySelectItem

@Composable
fun LedgerAgencySelectBottomSheet(
    modifier: Modifier = Modifier,
    currentAgencyId: Int,
    agencyList: List<MyAgencyEntity>,
    onClickItem: (agencyId: Int) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 24.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        agencyList.forEachIndexed { index, item ->
            LedgerAgencySelectItem(
                agencyEntity = item,
                currentAgency = currentAgencyId == item.id,
                onClick = onClickItem
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerAgencySelectBottomSheetPreview() {
    LedgerAgencySelectBottomSheet(
        currentAgencyId = 0,
        agencyList = listOf(MyAgencyEntity(0, "asddfkjfdsafhadskfjahdfkjldashfkdaslsdahfjk", 1, ""))
    ) {}
}
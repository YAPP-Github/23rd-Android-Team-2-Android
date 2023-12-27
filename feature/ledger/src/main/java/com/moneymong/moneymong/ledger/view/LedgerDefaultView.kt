package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.component.chip.MDSChip
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading5
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.ledger.view.item.LedgerTransactionItem

enum class LedgerTransactionType(
    val description: String,
    val imgRes: Int
) {
    전체(
        description = "기록된 장부가 없습니다",
        imgRes = drawable.img_transaction_empty
    ),
    지출(
        description = "지출기록이 없습니다",
        imgRes = drawable.img_expenditure_empty
    ),
    수입(
        description = "수입기록이 없습니다",
        imgRes = drawable.img_expenditure_empty
    )
}

@Composable
fun LedgerDefaultView(
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val lazyColumnState = rememberLazyListState()
    val chips = listOf(
        LedgerTransactionType.전체,
        LedgerTransactionType.지출,
        LedgerTransactionType.수입
    )
    val testList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15) // TODO

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        state = lazyColumnState
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "이만큼 남았어요",
                        style = Body3,
                        color = Gray07
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "512,000원", // TODO
                        style = Heading5,
                        color = Gray10
                    )
                }
                Image(
                    modifier = Modifier.size(width = 154.dp, height = 110.dp),
                    painter = painterResource(id = drawable.img_ledger_write),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gray01),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = drawable.ic_chevron_left),
                    contentDescription = null,
                    tint = Gray06
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "2023년 11월", // TODO
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = Gray03 // TODO 이번 달일 경우 우측 아이콘 비활성화
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            MDSChip(
                modifier = Modifier.padding(start = 20.dp),
                tabs = chips.map { it.name },
                selectedTabIndex = selectedTabIndex,
                onChangeSelectedTabIndex = { selectedTabIndex = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (testList.isEmpty()) { // TODO
            val descriptionDate =
                if (chips[selectedTabIndex] == LedgerTransactionType.전체) "11월에 " else "" // TODO
            item {
                Spacer(modifier = Modifier.height(121.dp))
                LedgerTransactionEmptyView(
                    text = descriptionDate + chips[selectedTabIndex].description,
                    image = chips[selectedTabIndex].imgRes
                )
            }
        } else {
            itemsIndexed(testList) { index, item -> // TODO
                LedgerTransactionItem(modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerDefaultPreview() {
    LedgerDefaultView()
}
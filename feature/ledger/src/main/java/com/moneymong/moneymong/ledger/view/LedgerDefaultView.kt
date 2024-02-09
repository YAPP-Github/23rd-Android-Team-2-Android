package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.common.ui.toWonFormat
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.component.chip.MDSChip
import com.moneymong.moneymong.design_system.loading.LoadingScreen
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading5
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.ledger.LedgerDetailEntity
import com.moneymong.moneymong.ledger.view.item.LedgerTransactionItem
import java.time.LocalDate

enum class LedgerTransactionType(
    val type: String,
    val description: String,
    val imgRes: Int
) {
    전체(
        type = "ALL",
        description = "기록된 장부가 없습니다",
        imgRes = drawable.img_transaction_empty
    ),
    지출(
        type = "EXPENSE",
        description = "지출기록이 없습니다",
        imgRes = drawable.img_expenditure_empty
    ),
    수입(
        type = "INCOME",
        description = "수입기록이 없습니다",
        imgRes = drawable.img_expenditure_empty
    )
}

@Composable
fun LedgerDefaultView(
    modifier: Modifier = Modifier,
    totalBalance: Int,
    ledgerDetails: List<LedgerDetailEntity>,
    transactionType: LedgerTransactionType,
    currentDate: LocalDate,
    hasTransaction: Boolean,
    isLoading: Boolean,
    onChangeTransactionType: (LedgerTransactionType) -> Unit,
    onAddMonthFromCurrentDate: (Long) -> Unit,
    onClickTransactionItem: (Int) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val lazyColumnState = rememberLazyListState()
    val chips = listOf(
        LedgerTransactionType.전체,
        LedgerTransactionType.지출,
        LedgerTransactionType.수입
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        state = lazyColumnState
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "이만큼 남았어요",
                        style = Body3,
                        color = Gray07
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = totalBalance.toString().toWonFormat(true),
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gray01),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.width(127.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .noRippleClickable {
                                onAddMonthFromCurrentDate(-1)
                            },
                        painter = painterResource(id = drawable.ic_chevron_left),
                        contentDescription = null,
                        tint = Gray06
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = "${currentDate.year}년 ${currentDate.month.value}월",
                        style = Body2,
                        color = Gray06
                    )
                    val isLastMonth =
                        currentDate.year == LocalDate.now().year && currentDate.monthValue == LocalDate.now().monthValue
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .noRippleClickable {
                                if (!isLastMonth) {
                                    onAddMonthFromCurrentDate(1)
                                }
                            },
                        painter = painterResource(id = drawable.ic_chevron_right),
                        contentDescription = null,
                        tint = if (isLastMonth) Gray03 else Gray06
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            MDSChip(
                modifier = Modifier.padding(start = 20.dp),
                tabs = chips.map { it.name },
                selectedTabIndex = selectedTabIndex,
                onChangeSelectedTabIndex = {
                    selectedTabIndex = it
                    onChangeTransactionType(chips[it])
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
        if (isLoading) {
            item {
                Spacer(modifier = Modifier.height(121.dp))
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }
        } else {
            if (hasTransaction) {
                itemsIndexed(ledgerDetails) { index, item ->
                    LedgerTransactionItem(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        ledgerDetail = item,
                        onClickTransactionItem = onClickTransactionItem
                    )
                }
            } else {
                val descriptionDate =
                    if (transactionType == LedgerTransactionType.전체) "${currentDate.monthValue}월에 " else ""
                item {
                    Spacer(modifier = Modifier.height(121.dp))
                    LedgerTransactionEmptyView(
                        text = descriptionDate + transactionType.description,
                        image = transactionType.imgRes
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerDefaultPreview() {
    LedgerDefaultView(
        totalBalance = 123123,
        ledgerDetails = emptyList(),
        transactionType = LedgerTransactionType.전체,
        currentDate = LocalDate.now(),
        hasTransaction = false,
        isLoading = false,
        onChangeTransactionType = {},
        onAddMonthFromCurrentDate = {},
        onClickTransactionItem = {}
    )
}
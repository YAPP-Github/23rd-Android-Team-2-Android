package com.moneymong.moneymong.ledger.view.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ext.toDateFormat
import com.moneymong.moneymong.common.ui.toWonFormat
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body1
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.domain.entity.ledger.LedgerDetailEntity
import com.moneymong.moneymong.domain.param.ledger.FundType

@Composable
fun LedgerTransactionItem(
    modifier: Modifier = Modifier,
    ledgerDetail: LedgerDetailEntity,
    onClickTransactionItem: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClickTransactionItem(ledgerDetail.id) }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(1.dp))
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = drawable.ic_transaction_index),
                contentDescription = null,
                tint = Blue01
            )
            Text(
                text = ledgerDetail.id.toString(),
                style = Body3,
                color = Blue04
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = ledgerDetail.storeInfo,
                style = Body4,
                color = Gray10
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ledgerDetail.paymentDate.toDateFormat("yyyy.MM.dd  HH:mm:ss"),
                style = Body1,
                color = Gray04
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            val amountColor = if (ledgerDetail.fundType == FundType.INCOME.name) Gray10 else Red03
            val sign = if (ledgerDetail.fundType == FundType.INCOME.name) FundType.INCOME.sign else FundType.EXPENSE.sign
            Text(
                text = "${sign}${ledgerDetail.amount.toString().toWonFormat()}원",
                style = Body4,
                color = amountColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "잔액 ${ledgerDetail.balance.toString().toWonFormat()}원",
                style = Body2,
                color = Gray06
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerTransactionItemPreview() {

}
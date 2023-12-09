package com.moneymong.moneymong.ledger.view.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun LedgerTransactionItem(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
                text = "23", // TODO
                style = Body3,
                color = Blue04
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "퍼스트유통", // TODO
                style = Body4,
                color = Gray10
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "2023.11.16 15:36", // TODO
                style = Body1,
                color = Gray04
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            val amountColor = if (true) Gray10 else Red03 // TODO 지출 수입 체크
            Text(
                text = "+1,600원", // TODO
                style = Body4,
                color = amountColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "잔액 512,000원", // TODO
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
package com.moneymong.moneymong.ledgermanual.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun LedgerManualTopbarView(
    modifier: Modifier = Modifier,
    onClickClose: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(
            modifier = Modifier
                .width(65.dp)
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "장부작성",
            style = Heading1,
            color = Gray10
        )
        Icon(
            modifier = Modifier
                .noRippleClickable {
                    onClickClose()
                }
                .padding(vertical = 10.dp, horizontal = MMHorizontalSpacing),
            painter = painterResource(id = R.drawable.ic_close_default),
            contentDescription = null,
            tint = Gray07
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerManualTopbarPreview() {
    LedgerManualTopbarView {

    }
}
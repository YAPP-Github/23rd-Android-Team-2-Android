package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1

@Composable
fun LedgerTopbarView(
    modifier: Modifier = Modifier,
    header: String,
    icon: Int,
    visibleArrow: Boolean = false,
    onClickDownArrow: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = header,
            style = Heading1,
            color = Gray10
        )
        Spacer(modifier = Modifier.width(4.dp))
        if (visibleArrow) {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .noRippleClickable {
                    onClickDownArrow()
                },
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Gray10
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerTopbarPreview() {
    LedgerTopbarView(header = "장부", icon = 0) {}
}
package com.moneymong.moneymong.ledger.view.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.SkyBlue01

@Composable
fun LedgerAgencySelectItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .border(width = 1.dp, color = Blue04, shape = RoundedCornerShape(16.dp)) // TODO
            .background(color = Blue01) // TODO
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(100))
                .background(SkyBlue01), // TODO
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = drawable.img_club),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "Yapp", // TODO
                style = Body3,
                color = Blue04 // TODO
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "멤버수 10", // TODO
                style = Body2,
                color = Gray05
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(1f))
        Icon(
            painter = painterResource(id = drawable.ic_check),
            contentDescription = null,
            tint = Blue04 // TODO
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerStaffSelectItemPreview() {
    LedgerAgencySelectItem() {}
}
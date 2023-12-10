package com.moneymong.moneymong.ocr_result.view.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.moneymong.moneymong.design_system.R.drawable
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray08

@Composable
fun OCRResultBottomItem(
    modifier: Modifier = Modifier,
    icon: Int,
    prefix: String,
    suffix: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Blue04
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${prefix}:",
            style = Body3,
            color = Gray06
        )
        Text(
            text = " $suffix",
            style = Body3,
            color = Gray08
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultBottomItemPreview() {
    OCRResultBottomItem(icon = drawable.ic_money, prefix = "금액", suffix = "1,800원")
}
package com.moneymong.moneymong.ocr_result.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun OCRResultTopbarView(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickClose: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = MMHorizontalSpacing)
                .noRippleClickable { onClickBack() },
            painter = painterResource(id = R.drawable.ic_chevron_left),
            contentDescription = null,
            tint = Color.White
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "스캔결과",
            style = Heading1,
            color = White
        )
        Icon(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = MMHorizontalSpacing)
                .noRippleClickable { onClickClose() },
            painter = painterResource(id = R.drawable.ic_close_default),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultTopbarPreview() {
    OCRResultTopbarView(onClickBack = { /*TODO*/ }) {
        
    }
}
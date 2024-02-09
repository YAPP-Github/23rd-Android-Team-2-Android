package com.moneymong.moneymong.ocr_detail.view

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun OCRDetailTopbarView(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClickPrev: () -> Unit,
    onClickRegister: () -> Unit
) {
    val textColor = if (enabled) Blue04 else Gray04
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .noRippleClickable { onClickPrev() }
                .padding(start = 20.dp),
            painter = painterResource(id = drawable.ic_chevron_left),
            contentDescription = null,
            tint = Gray10
        )
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
            text = "상세내역",
            style = Heading1,
            color = Gray10
        )
        Text(
            modifier = Modifier
                .noRippleClickable {
                    if (enabled) {
                        onClickRegister()
                    }
                }
                .padding(end = 20.dp),
            text = "등록하기",
            style = Body2,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OCRDetailTopbarPreview() {
    OCRDetailTopbarView(
        enabled = true,
        onClickPrev = { /*TODO*/ }) {

    }
}
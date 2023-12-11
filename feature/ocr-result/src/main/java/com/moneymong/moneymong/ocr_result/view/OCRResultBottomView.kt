package com.moneymong.moneymong.ocr_result.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.ocr_result.R
import com.moneymong.moneymong.ocr_result.view.item.OCRResultBottomItem

fun Icon(painter: Int, contentDescription: Any?) {

}

@Composable
fun OCRResultBottomView(
    modifier: Modifier = Modifier,
    source: String,
    amount: Long,
    date: String,
    onClickRetryOCR: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(Gray07)
                .clickable { onClickRetryOCR() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                modifier = Modifier.padding(vertical = 8.dp),
                painter = painterResource(id = drawable.ic_scan),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "다시 찍기",
                style = Body4,
                color = White
            )
            Spacer(modifier = Modifier.width(20.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                OCRResultBottomItem(
                    modifier = Modifier.fillMaxWidth(),
                    icon = drawable.ic_agency,
                    prefix = "수입·지출 출처",
                    suffix = "팬도로시(아주대학생회관점)" // TODO
                )
                Spacer(modifier = Modifier.height(14.dp))
                OCRResultBottomItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    icon = drawable.ic_agency,
                    prefix = "금액",
                    suffix = "1,800원" //TODO
                )
                Spacer(modifier = Modifier.height(14.dp))
                OCRResultBottomItem(
                    modifier = Modifier.fillMaxWidth(),
                    icon = drawable.ic_agency,
                    prefix = "날짜",
                    suffix = "2023년 11월 15일" // TODO
                )
                Spacer(modifier = Modifier.height(14.dp))
                OCRResultBottomItem(
                    modifier = Modifier.fillMaxWidth(),
                    icon = drawable.ic_agency,
                    prefix = "시간",
                    suffix = "14:02" // TODO
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MDSButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = "수정하기",
                        size = MDSButtonSize.LARGE,
                        type = MDSButtonType.SECONDARY,
                        onClick = { /*TODO*/ }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MDSButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = "등록하기",
                        size = MDSButtonSize.LARGE,
                        type = MDSButtonType.PRIMARY,
                        onClick = { /*TODO*/ }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultBottomPreview() {
    OCRResultBottomView(
        source = "",
        amount = 0L,
        date = "",
        onClickRetryOCR = {}
    )
}
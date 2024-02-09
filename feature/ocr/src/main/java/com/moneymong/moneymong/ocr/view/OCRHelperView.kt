package com.moneymong.moneymong.ocr.view

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Heading2

@Composable
fun OCRHelperView(
    modifier: Modifier = Modifier,
    onClickClose: () -> Unit
) {
    Box(modifier = modifier
        .fillMaxSize()
        .noRippleClickable { onClickClose() }
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_background_blur),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .noRippleClickable { onClickClose() },
                painter = painterResource(id = R.drawable.ic_close_default),
                contentDescription = null,
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "실물 영수증 혹은 전자 영수증을\n활영해 주세요.",
                style = Heading2,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "텍스트가 육안으로 보일 수준으로\n찍어야 정확하게 인식할 수 있습니다.",
                style = Body4,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "점포명, 날짜와 시간, 합계 금액이 정확하게\n스캔되도록 영수증의 처음과 끝이\n모두 포함되게 인식해주세요.",
                style = Body4,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                modifier = Modifier.size(width = 320.dp, height = 300.dp),
                painter = painterResource(id = R.drawable.img_ocr_guide),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRHelperPreview() {
    OCRHelperView {
    }
}
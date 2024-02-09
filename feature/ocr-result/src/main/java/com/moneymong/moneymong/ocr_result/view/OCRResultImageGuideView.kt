package com.moneymong.moneymong.ocr_result.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun OCRResultImageGuideView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(width = 230.dp, height = 84.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = drawable.img_ocr_result_guide_background),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = drawable.ic_zoom_in),
                contentDescription = null,
                tint = White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "이미지를 확대/축소 해보세요",
                style = Heading1,
                color = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultImageGuidePreview() {
    OCRResultImageGuideView()
}
package com.moneymong.moneymong.ocr.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.Mint03

@Composable
fun OCRCameraPermissionDeniedView(
    modifier: Modifier = Modifier,
    onClickRequestPermission: () -> Unit
) {
    Column(
        modifier = modifier
            .background(Gray10),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(88.dp),
            painter = painterResource(id = drawable.img_camera_permission),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "머니몽에서 카메라\n접근 권한이 필요합니다",
            style = Heading1,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "영수증을 스캔하기 위해 필요합니다",
            style = Body3,
            color = Gray03
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.noRippleClickable { onClickRequestPermission() },
            text = "설정 열기",
            style = Body3,
            color = Mint03
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OCRCameraPermissionDeniedPreview() {
    OCRCameraPermissionDeniedView(
        onClickRequestPermission = {}
    )
}
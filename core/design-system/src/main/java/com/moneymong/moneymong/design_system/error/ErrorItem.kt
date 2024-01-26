package com.moneymong.moneymong.design_system.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray07

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Gray07,
            style = Body3
        )
        Spacer(modifier = Modifier.height(8.dp))
        MDSButton(
            onClick = onRetry,
            text = "다시 시도",
            size = MDSButtonSize.SMALL,
            contentHorizontalPadding = 24.dp
        )
    }
}

@Preview
@Composable
fun ErrorItemPreview() {
    ErrorItem(
        modifier = Modifier.fillMaxWidth(),
        message = "문제가 발생했습니다\n다시 시도해주세요",
        onRetry = {}
    )
}
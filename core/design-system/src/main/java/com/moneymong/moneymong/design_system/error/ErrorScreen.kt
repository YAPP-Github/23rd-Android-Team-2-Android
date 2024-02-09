package com.moneymong.moneymong.design_system.error

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray07

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(size = 100.dp),
            painter = painterResource(id = R.drawable.img_profile_blue),
            contentDescription = "error"
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Gray07,
            style = Body4
        )
        Spacer(modifier = Modifier.height(12.dp))
        MDSButton(
            onClick = onRetry,
            text = "다시 시도하기",
            size = MDSButtonSize.SMALL,
            contentHorizontalPadding = 22.dp
        )
    }
}


@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        modifier = Modifier.fillMaxSize(),
        message = "문제가 발생했습니다\n다시 시도해주세요",
        onRetry = { Log.i("test", "retry") }
    )
}
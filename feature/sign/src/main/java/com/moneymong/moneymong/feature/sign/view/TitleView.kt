package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Heading4
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun TitleView(mainText: String, subText: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mainText,
            style = Heading4,
            color = White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = subText,
            style = Body4,
            color = White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleViewPreview() {
    TitleView("교내 회계관리를 편리하게", "수기 기록은 이제 그만!.간단하게 기록해요.")
}

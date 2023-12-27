package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Heading3

@Composable
fun TitleView(text: String) {
    Column(
        horizontalAlignment = Alignment.Start
    ){
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = text,
            style = Heading3,
            color = Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleViewPreview() {
    TitleView("교내 회계관리를 편리하게\n시작해보세요.")
}

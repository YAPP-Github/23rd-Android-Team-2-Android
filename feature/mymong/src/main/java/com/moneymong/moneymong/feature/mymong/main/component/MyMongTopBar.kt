package com.moneymong.moneymong.feature.mymong.main.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1

@Composable
fun MyMongTopBar(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(vertical = 16.dp),
        text = "마이몽",
        color = Gray10,
        style = Heading1
    )
}
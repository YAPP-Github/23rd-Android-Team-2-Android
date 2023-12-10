package com.moneymong.moneymong.ocr.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R.*

@Composable
fun OCRTopbarView(
    modifier: Modifier = Modifier,
    onClickHelp: () -> Unit,
    onClickClose: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.6f)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            painter = painterResource(id = drawable.ic_warning_outline),
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            painter = painterResource(id = drawable.ic_close_default),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OCRTopbarPreview() {
    OCRTopbarView(onClickHelp = { /*TODO*/ }) {
        
    }
}
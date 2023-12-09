package com.moneymong.moneymong.ledger.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.ledger.R

@Composable
fun LedgerAdminEmptyView(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(230.dp),
                painter = painterResource(id = R.drawable.img_empty_scan),
                contentDescription = null
            )
            Text(
                text = "카메라로 영수증을 스캔해서\n손쉽게 장부를 기록하세요",
                style = Body4,
                color = Gray07,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LedgerAdminEmptyPreview() {
    LedgerAdminEmptyView()
}
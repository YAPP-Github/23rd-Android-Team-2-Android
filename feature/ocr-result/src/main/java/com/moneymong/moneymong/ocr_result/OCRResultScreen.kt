package com.moneymong.moneymong.ocr_result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.ocr_result.view.OCRResultBottomView
import com.moneymong.moneymong.ocr_result.view.OCRResultTopbarView

@Composable
fun OCRResultScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Black)
                .padding(it)
        ) {
            OCRResultTopbarView(
                modifier = Modifier.align(Alignment.TopCenter),
                onClickBack = { /*TODO*/ },
                onClickClose = { /*TODO*/ }
            )
            OCRResultBottomView(
                modifier = Modifier.align(Alignment.BottomCenter),
                source = "", // TODO
                amount = 0L, // TODO
                date = "", // TODO
                onClickRetryOCR = { /*TODO*/ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultScreenPreview() {
    OCRResultScreen()
}
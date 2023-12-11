package com.moneymong.moneymong.ocr_result

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.ocr_result.view.OCRResultBottomView
import com.moneymong.moneymong.ocr_result.view.OCRResultImageGuideView
import com.moneymong.moneymong.ocr_result.view.OCRResultTopbarView

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OCRResultScreen(
    modifier: Modifier = Modifier
) {
    var visibleGuide by remember { mutableStateOf(true) }
    var scale by remember { mutableStateOf(1f) }

    Scaffold {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Black)
                .padding(it)
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 214.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            if (visibleGuide) visibleGuide = false
                            scale = (scale * zoom).coerceIn(1f, 3f)
                        }
                    },
                model = "https://dimg.donga.com/wps/NEWS/IMAGE/2023/05/12/119255016.1.jpg", // TODO 현재는 고양이 사진으로 대체
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
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
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = visibleGuide,
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = FastOutLinearInEasing
                    )
                )
            ) {
                OCRResultImageGuideView()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRResultScreenPreview() {
    OCRResultScreen()
}
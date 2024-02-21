package com.moneymong.moneymong.ocr.view

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Mint03

@Composable
fun OCRTopbarView(
    modifier: Modifier = Modifier,
    visibleHelp: Boolean,
    onClickHelp: () -> Unit,
    onClickClose: () -> Unit
) {
    val shadowBlurRadius = 12.dp
    val shadowColor = Mint03.copy(alpha = 0.57f).toArgb()

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.6f)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (visibleHelp) {
                Icon(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 20.dp)
                        .noRippleClickable { onClickHelp() },
                    painter = painterResource(id = drawable.ic_warning_outline),
                    contentDescription = null,
                    tint = Color.White
                )
            } else {
                Spacer(modifier = Modifier)
            }
            Icon(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
                    .noRippleClickable { onClickClose() },
                painter = painterResource(id = drawable.ic_close_default),
                contentDescription = null,
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 56.dp, end = 20.dp)
                .drawBehind {
                    drawIntoCanvas {
                        val paint = Paint()
                        val frameworkPaint = paint.asFrameworkPaint()
                        frameworkPaint.color = shadowColor
                        frameworkPaint.maskFilter = BlurMaskFilter(
                            shadowBlurRadius.toPx(),
                            BlurMaskFilter.Blur.NORMAL
                        )
                        it.drawRect(
                            left = 0f,
                            top = 0f,
                            right = size.width,
                            bottom = size.height,
                            paint = paint
                        )
                    }
                }
                .height(4.dp)
                .clip(RoundedCornerShape(100))
                .background(Mint03)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OCRTopbarPreview() {
    OCRTopbarView(visibleHelp = false, onClickHelp = { /*TODO*/ }) {

    }
}
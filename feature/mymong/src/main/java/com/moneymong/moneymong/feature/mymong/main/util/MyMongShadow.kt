package com.moneymong.moneymong.feature.mymong.main.util

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

internal fun Modifier.myMongRoundRectShadow(): Modifier {
    val borderRadius = 12.dp
    val shadowBlurRadius = 12.dp
    val shadowColor = Color(0xFF6D6D6D).copy(alpha = 0.08f).toArgb()

    return this.drawBehind {
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = shadowColor
            frameworkPaint.maskFilter = BlurMaskFilter(
                shadowBlurRadius.toPx(),
                BlurMaskFilter.Blur.NORMAL
            )
            it.drawRoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint = paint
            )
        }
    }
}
package com.example.member.util

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Gray02

internal fun Modifier.MemberRoundRectShadow(): Modifier {
    val borderRadius = 16.dp
    val shadowBlurRadius = 12.dp
    val shadowColor = Color(0xFF6D6D6D).copy(alpha = 0.08f).toArgb()
    val borderColor = Gray02
    val borderWidth = 2.dp

    return this.drawBehind {
        drawIntoCanvas { canvas ->
            // 그림자 그리기
            val shadowPaint = Paint().apply {
                asFrameworkPaint().apply {
                    color = shadowColor
                    maskFilter = BlurMaskFilter(shadowBlurRadius.toPx(), BlurMaskFilter.Blur.NORMAL)
                }
            }
            canvas.drawRoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint = shadowPaint
            )

            // 테두리 그리기
            val borderPaint = Paint().apply {
                asFrameworkPaint().apply {
                    color = borderColor.toArgb()
                    style = android.graphics.Paint.Style.STROKE
                    strokeWidth = borderWidth.toPx()
                }
            }
            canvas.drawRoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint = borderPaint
            )
        }
    }
}


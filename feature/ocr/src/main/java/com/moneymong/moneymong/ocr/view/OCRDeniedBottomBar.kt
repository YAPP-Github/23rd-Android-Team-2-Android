package com.moneymong.moneymong.ocr.view

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Mint03

@Composable
fun OCRDeniedBottomBar(
    modifier: Modifier = Modifier
) {
    val shadowBlurRadius = 12.dp
    val shadowColor = Mint03.copy(alpha = 0.57f).toArgb()
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Black.copy(alpha = 0.6f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(60.dp),
                painter = painterResource(id = R.drawable.ic_camera_btn),
                contentDescription = null,
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 106.dp, end = 20.dp)
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
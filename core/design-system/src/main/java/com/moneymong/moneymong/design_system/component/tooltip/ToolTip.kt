package com.moneymong.moneymong.design_system.component.tooltip

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray07


@Composable
fun MDSToolTip(
    modifier: Modifier = Modifier,
    text: String,
    position: MDSToolTipPosition
) {
    val shadowBlurRadius = 8.dp
    val shadowColor = Black.copy(alpha = 0.3f).toArgb()

    Column(modifier = modifier.drawBehind {
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
                bottom = size.height - 12,
                paint = paint
            )
        }
    }) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Gray07),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
                text = text,
                style = Body3,
                color = Color.White
            )
        }
        val align = when(position) {
            MDSToolTipPosition.Left -> Alignment.Start
            MDSToolTipPosition.Right -> Alignment.End
        }
        Icon(
            modifier = Modifier
                .align(align)
                .padding(start = position.paddingStart.dp, end = position.paddingEnd.dp),
            painter = painterResource(id = R.drawable.ic_polygon),
            contentDescription = null,
            tint = Gray07
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MDSToolTipPreview() {
    MDSToolTip(
        text = "해당 기능을 사용해보세요",
        position = MDSToolTipPosition.Right
    )
}
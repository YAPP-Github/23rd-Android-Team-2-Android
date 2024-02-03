package com.moneymong.moneymong.design_system.component.button

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Black

@Composable
fun MDSFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconResource: Int,
    iconSize: FABIconSize = FABIconSize(width = 24.dp, height = 24.dp),
    containerColor: Color,
    contentColor: Color = Color.Unspecified
) {
    val shadowOffsetY = 2.dp
    val shadowBlurRadius = 8.dp
    val shadowColor = Black.copy(alpha = 0.3f).toArgb()

    FloatingActionButton(
        modifier = modifier
            .size(48.dp)
            .drawBehind {
                drawIntoCanvas {
                    val paint = Paint()
                    val frameworkPaint = paint.asFrameworkPaint()
                    frameworkPaint.color = shadowColor
                    frameworkPaint.maskFilter = BlurMaskFilter(
                        shadowBlurRadius.toPx(),
                        BlurMaskFilter.Blur.NORMAL
                    )
                    it.drawOval(
                        left = 0f,
                        top = shadowOffsetY.toPx(),
                        right = size.width,
                        bottom = size.height + shadowOffsetY.toPx(),
                        paint = paint
                    )
                }
            },
        onClick = onClick,
        shape = CircleShape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        )
    ) {
        Icon(
            modifier = Modifier.size(iconSize.width, iconSize.height),
            painter = painterResource(id = iconResource),
            contentDescription = "Floating Action Button",
        )
    }
}

data class FABIconSize(
    val width: Dp,
    val height: Dp
)
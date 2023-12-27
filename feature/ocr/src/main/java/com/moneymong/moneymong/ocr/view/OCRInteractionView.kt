package com.moneymong.moneymong.ocr.view

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.ocr.OCRViewModel
import com.moneymong.moneymong.ocr.util.bounceClick

@Composable
fun OCRInteractionView(
    modifier: Modifier = Modifier,
    viewModel: OCRViewModel = hiltViewModel()
) {
    val shadowBlurRadius = 12.dp
    val shadowColor = Mint03.copy(alpha = 0.57f).toArgb()

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Black.copy(alpha = 0.6f))
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 36.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(width = 1.dp, color = Gray02, shape = RoundedCornerShape(8.dp))
                    .background(Gray05),
                contentAlignment = Alignment.Center
            ) {
                if (false) { // TODO 갤러리 접근 권한이 있다면
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = 0), // TODO
                        contentDescription = null
                    )
                }

            }
            Icon(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(60.dp)
                    .bounceClick { viewModel.onClickTakePicture() },
                painter = painterResource(id = drawable.ic_camera_btn),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(76.dp))
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

@Preview(showBackground = true)
@Composable
fun OCRInteractionPreview() {
    OCRInteractionView()
}
package com.moneymong.moneymong.ocr.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.ocr.util.bounceClick

@Composable
fun OCRInteractionView(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Black.copy(alpha = 0.6f)),
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
                .bounceClick { /*TODO*/ },
            painter = painterResource(id = drawable.ic_camera_btn),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(76.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun OCRInteractionPreview() {
    OCRInteractionView()
}
package com.moneymong.moneymong.design_system.component.tag

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MDSTag(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    @DrawableRes iconResource: Int? = null,
) {
    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = Int.MAX_VALUE.dp)
            )
            .padding(horizontal = 8.dp, vertical = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = contentColor,
            style = Body2,
        )
        if (iconResource != null) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = iconResource),
                contentDescription = "Tag icon",
                tint = contentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MDSTagPreview() {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MDSTag(
            text = "tag",
            backgroundColor = Blue04,
            contentColor = White,
        )
        MDSTag(
            text = "tag",
            backgroundColor = Blue04,
            contentColor = White,
            iconResource = com.moneymong.moneymong.design_system.R.drawable.ic_pencil
        )
    }
}
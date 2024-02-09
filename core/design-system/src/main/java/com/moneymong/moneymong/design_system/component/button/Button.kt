package com.moneymong.moneymong.design_system.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body3

@Composable
fun MDSButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    type: MDSButtonType = MDSButtonType.PRIMARY,
    size: MDSButtonSize = MDSButtonSize.LARGE,
    @DrawableRes iconResource: Int? = null,
    enabled: Boolean = true,
    contentHorizontalPadding: Dp = 0.dp,
) {
    val backgroundColor = if (enabled) type.backgroundColor else disabledBackgroundColor
    val contentColor = if (enabled) type.contentColor else disabledContentColor

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(
                RoundedCornerShape(12.dp)
            )
            .clickable(
                onClick = onClick,
                enabled = enabled
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = size.verticalPadding,
                horizontal = contentHorizontalPadding
            ),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (iconResource != null) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = iconResource),
                    contentDescription = "Button icon",
                    tint = contentColor
                )
            }
            Text(
                text = text,
                color = contentColor,
                style = Body3,
            )
        }
    }
}
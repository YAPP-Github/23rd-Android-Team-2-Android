package com.moneymong.moneymong.feature.agency.search.item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue03
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.search.item.BottomSheetItemDefaults.backgroundColor
import com.moneymong.moneymong.feature.agency.search.item.BottomSheetItemDefaults.borderColor
import com.moneymong.moneymong.feature.agency.search.item.BottomSheetItemDefaults.iconTint
import com.moneymong.moneymong.feature.agency.search.item.BottomSheetItemDefaults.messageColor

@Composable
internal fun BottomSheetItem(
    modifier: Modifier = Modifier,
    @DrawableRes imgRes: Int,
    message: String,
    isChecked: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 16.dp))
            .clickable(enabled = enabled) {
                onClick()
            },
        shape = RoundedCornerShape(size = 16.dp),
        color = backgroundColor(enabled, isChecked),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor(enabled, isChecked)
        )
    ) {
        BottomSheetItemContent(
            imgRes = imgRes,
            message = message,
            messageColor = messageColor(enabled, isChecked),
            iconTint = iconTint(enabled, isChecked)
        )
    }
}

@Composable
private fun BottomSheetItemContent(
    modifier: Modifier = Modifier,
    @DrawableRes imgRes: Int,
    message: String,
    messageColor: Color,
    iconTint: Color,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomSheetItemContentImage(imgRes = imgRes)
        Spacer(modifier = Modifier.width(10.dp))
        BottomSheetItemContentMessage(
            modifier = Modifier.weight(1f),
            message = message,
            messageColor = messageColor
        )
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "check icon",
            tint = iconTint
        )
    }
}

@Composable
private fun BottomSheetItemContentImage(
    modifier: Modifier = Modifier,
    @DrawableRes imgRes: Int
) {
    Image(
        modifier = modifier.size(44.dp),
        painter = painterResource(id = imgRes),
        contentDescription = "agency image"
    )
}

@Composable
private fun BottomSheetItemContentMessage(
    modifier: Modifier = Modifier,
    message: String,
    messageColor: Color,
) {
    Text(
        modifier = modifier,
        text = message,
        color = messageColor,
        style = Body3
    )
}


private object BottomSheetItemDefaults {
    val backgroundColor: (enabled: Boolean, isChecked: Boolean) -> Color = { enabled, isChecked ->
        if (enabled) {
            if (isChecked) Blue01 else White
        } else {
            Gray02
        }
    }

    val borderColor: (enabled: Boolean, isChecked: Boolean) -> Color = { enabled, isChecked ->
        if (enabled) {
            if (isChecked) Blue03 else Gray02
        } else {
            Gray02
        }
    }

    val messageColor: (enabled: Boolean, isChecked: Boolean) -> Color = { enabled, isChecked ->
        if (enabled) {
            if (isChecked) Blue04 else Gray06
        } else {
            Gray04
        }
    }

    val iconTint = { enabled: Boolean, isChecked: Boolean ->
        if (enabled) {
            if (isChecked) Blue04 else Gray03
        } else {
            Gray04
        }
    }
}
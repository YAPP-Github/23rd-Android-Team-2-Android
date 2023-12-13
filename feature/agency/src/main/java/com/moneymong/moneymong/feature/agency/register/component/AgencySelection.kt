package com.moneymong.moneymong.feature.agency.register.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun AgencySelection( // todo: MDSSelection 으로 변경 예정
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    type: AgencySelectionType = AgencySelectionType.PRIMARY,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) type.backgroundColor else unSelectedBackgroundColor
    val contentColor =
        if (isSelected) type.contentColor else unSelectedContentColor
    val borderColor =
        if (isSelected) type.backgroundColor else Gray03

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = backgroundColor)
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = text,
            color = contentColor,
            style = Body3,
        )
    }
}


enum class AgencySelectionType(
    val backgroundColor: Color,
    val contentColor: Color
) {
    PRIMARY(
        backgroundColor = Blue04,
        contentColor = White
    ),
    SECONDARY(
        backgroundColor = Blue01,
        contentColor = Blue04
    ),
}

internal val unSelectedBackgroundColor = White
internal val unSelectedContentColor = Gray05
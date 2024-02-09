package com.moneymong.moneymong.design_system.component.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray03

@Composable
fun MDSSelection(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    type: MDSSelectionType = MDSSelectionType.PRIMARY,
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

@Preview(showBackground = true)
@Composable
private fun MDSSelectionPreview() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MDSSelection(
            modifier = Modifier.weight(1f),
            text = "동아리",
            isSelected = true,
            onClick = {}
        )
        MDSSelection(
            modifier = Modifier.weight(1f),
            text = "나는 Secondary",
            isSelected = true,
            type = MDSSelectionType.SECONDARY,
            onClick = {}
        )
        MDSSelection(
            modifier = Modifier.weight(1f),
            text = "학생회",
            isSelected = false,
            onClick = {}
        )
    }
}
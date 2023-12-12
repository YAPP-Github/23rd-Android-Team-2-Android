package com.moneymong.moneymong.design_system.component.selection

import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.White


enum class MDSSelectionType(
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
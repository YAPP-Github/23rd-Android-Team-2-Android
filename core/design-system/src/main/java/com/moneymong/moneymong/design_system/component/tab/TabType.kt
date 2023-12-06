package com.moneymong.moneymong.design_system.component.tab

import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray10

enum class MDSTabType(
    val contentColor: Color,
    val indicatorColor: Color
) {
    Selected(
        contentColor = Gray10,
        indicatorColor = Blue04
    ),
    UnSelected(
        contentColor = Gray04,
        indicatorColor = Gray03
    )
}
package com.moneymong.moneymong.design_system.component.button

import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray04
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.White

enum class MDSButtonType(val backgroundColor: Color, val contentColor: Color) {

    PRIMARY(backgroundColor = Blue04, contentColor = White),
    SECONDARY(backgroundColor = Blue01, contentColor = Blue04),
    NEGATIVE(backgroundColor = Gray02, contentColor = Gray05),
}

val disabledBackgroundColor = Gray03
val disabledContentColor = Gray04
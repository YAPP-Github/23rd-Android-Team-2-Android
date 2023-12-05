package com.moneymong.moneymong.design_system.component.infor

import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Red01
import com.moneymong.moneymong.design_system.theme.Red03

enum class MDSInforType(val backgroundColor: Color, val contentColor: Color) {

    PRIMARY(backgroundColor = Blue01, contentColor = Blue04),
    SECONDARY(backgroundColor = Gray01, contentColor = Gray05),
    NEGATIVE(backgroundColor = Red01, contentColor = Red03),
}
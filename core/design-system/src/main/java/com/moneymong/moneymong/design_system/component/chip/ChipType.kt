package com.moneymong.moneymong.design_system.component.chip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray05

enum class ChipType(
    val borderColor: Color,
    val backgroundColor: Color,
    val textColor: Color
) {
    Selected(
        borderColor = Blue04,
        backgroundColor = Blue04,
        textColor = Color.White
    ),
    UnSelected(
        borderColor = Gray03,
        backgroundColor = Color.White,
        textColor = Gray05
    )
}

class MDSChipColor internal constructor(
    private val mdsChipType: ChipType
) {
    @Composable
    internal fun borderColor() = rememberUpdatedState(mdsChipType.borderColor)

    @Composable
    internal fun backgroundColor() = rememberUpdatedState(mdsChipType.backgroundColor)

    @Composable
    internal fun textColor() = rememberUpdatedState(mdsChipType.textColor)
}
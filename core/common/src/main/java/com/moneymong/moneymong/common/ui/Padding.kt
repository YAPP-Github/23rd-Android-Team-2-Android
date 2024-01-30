package com.moneymong.moneymong.common.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection


operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    return LayoutDirection.Ltr.let { dir ->
        PaddingValues(
            start = calculateStartPadding(dir) + other.calculateStartPadding(dir),
            end = calculateEndPadding(dir) + other.calculateEndPadding(dir),
            top = calculateTopPadding() + other.calculateTopPadding(),
            bottom = calculateBottomPadding() + other.calculateBottomPadding()
        )
    }
}
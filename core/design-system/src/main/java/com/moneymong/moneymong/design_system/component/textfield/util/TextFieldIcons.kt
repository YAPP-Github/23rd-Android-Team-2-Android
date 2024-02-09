package com.moneymong.moneymong.design_system.component.textfield.util

import androidx.compose.ui.graphics.Color
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Gray04

enum class MDSTextFieldIcons(
    internal val resourceId: Int,
    internal val color: (state: MDSTextFieldState) -> Color
) {
    Clear(
        resourceId = R.drawable.ic_close_default,
        color = { state ->
            when (state) {
                is MDSTextFieldState.Default -> Gray04
                is MDSTextFieldState.Active -> Gray04
                is MDSTextFieldState.Error -> Gray04
                is MDSTextFieldState.Filled -> Gray04
            }
        }
    ),
    Search(
        resourceId = R.drawable.ic_search,
        color = { state ->
            when (state) {
                is MDSTextFieldState.Default -> Gray04
                is MDSTextFieldState.Active -> Blue04
                is MDSTextFieldState.Error -> Gray04
                is MDSTextFieldState.Filled -> Gray04
            }
        }
    );

    internal fun visibleByState(state: MDSTextFieldState): Boolean {
        return when (this) {
            Clear -> state is MDSTextFieldState.Active || state is MDSTextFieldState.Error
            Search -> state is MDSTextFieldState.Default || state is MDSTextFieldState.Filled
                    || state is MDSTextFieldState.Active
        }
    }

    internal fun clearFocusWhenClicked(): Boolean {
        return when (this) {
            Clear -> false
            Search -> true
        }
    }
}
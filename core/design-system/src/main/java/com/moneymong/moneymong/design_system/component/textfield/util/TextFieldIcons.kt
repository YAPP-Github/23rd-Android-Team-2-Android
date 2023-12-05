package com.moneymong.moneymong.design_system.component.textfield.util

import com.moneymong.moneymong.design_system.R

enum class MDSTextFieldIcons(val resourceId: Int) {
    Clear(resourceId = R.drawable.ic_close_default),
    Search(resourceId = R.drawable.ic_search);

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
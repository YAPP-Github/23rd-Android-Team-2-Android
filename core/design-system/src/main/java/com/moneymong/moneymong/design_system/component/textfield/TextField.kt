package com.moneymong.moneymong.design_system.component.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons


@Composable
fun MDSTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    title: String,
    placeholder: String,
    isFilled: Boolean,
    isError: Boolean = false,
    helperText: String? = null,
    maxCount: Int? = null,
    singleLine: Boolean = true,
    icon: MDSTextFieldIcons? = null,
    onIconClick: (() -> Unit) = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    MDSBaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        title = title,
        placeholder = placeholder,
        isFilled = isFilled,
        isError = isError,
        helperText = helperText,
        maxCount = maxCount,
        singleLine = singleLine,
        icon = icon,
        onIconClick = onIconClick,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}
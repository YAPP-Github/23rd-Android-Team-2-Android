package com.moneymong.moneymong.design_system.component.textfield

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
    isError: Boolean = false,
    isFilled: Boolean = false,
    helperText: String? = null,
    maxCount: Int? = null,
    singleLine: Boolean = true,
    onDone: () -> Unit = {},
    icon: MDSTextFieldIcons? = null,
    onIconClick: (() -> Unit) = {},
) {

    MDSBaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        title = title,
        placeholder = placeholder,
        isError = isError,
        isFilled = isFilled,
        helperText = helperText,
        maxCount = maxCount,
        singleLine = singleLine,
        onDone = onDone,
        icon = icon,
        onIconClick = onIconClick,
    )
}
package com.moneymong.moneymong.design_system.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.design_system.component.textfield.util.MDSNumberTextFieldType
import com.moneymong.moneymong.design_system.component.textfield.util.PriceVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons


@Composable
fun MDSNumberTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    type: MDSNumberTextFieldType,
    title: String,
    placeholder: String,
    isFilled: Boolean,
    onFocused: (() -> Unit),
    onDone: () -> Unit,
    onIconClick: (() -> Unit),
    singleLine: Boolean = true,
) {

    MDSBaseTextField(
        modifier = modifier,
        value = value.copy(text = value.text.filter { it.isDigit() }.trimStart { it == '0' }),
        onValueChange = onValueChange,
        title = title,
        placeholder = placeholder,
        isFilled = isFilled,
        onFocused = onFocused,
        singleLine = singleLine,
        onDone = onDone,
        icon = MDSTextFieldIcons.Clear,
        onIconClick = onIconClick,
        visualTransformation = PriceVisualTransformation(type),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
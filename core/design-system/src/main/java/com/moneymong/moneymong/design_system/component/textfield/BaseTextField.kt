package com.moneymong.moneymong.design_system.component.textfield

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldContainer
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.component.textfield.util.getMDSTextFieldState
import com.moneymong.moneymong.design_system.theme.Body3

@Composable
internal fun MDSBaseTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    title: String,
    placeholder: String,
    isFilled: Boolean,
    isError: Boolean = false,
    helperText: String? = null,
    maxCount: Int? = null,
    singleLine: Boolean,
    minLines: Int = 1,
    icon: MDSTextFieldIcons? = null,
    onIconClick: (() -> Unit) = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    val focusManager = LocalFocusManager.current

    val state = getMDSTextFieldState(text = value.text, isError = isError, isFilled = isFilled)

    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .sizeIn(minWidth = 320.dp)
            .width(intrinsicSize = IntrinsicSize.Min),
        textStyle = Body3.copy(
            color = state.textColor
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            MDSTextFieldContainer(
                title = title,
                state = state,
                count = value.text.length,
                placeholder = placeholder,
                icon = icon,
                onIconClick = {
                    onIconClick()
                    if (icon?.clearFocusWhenClicked() == true) {
                        focusManager.clearFocus()
                    }
                },
                maxCount = maxCount,
                helperText = helperText,
                innerTextField = innerTextField
            )
        }
    )
}
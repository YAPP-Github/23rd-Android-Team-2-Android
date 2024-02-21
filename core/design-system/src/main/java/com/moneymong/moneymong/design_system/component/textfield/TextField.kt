package com.moneymong.moneymong.design_system.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.theme.Red03


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
    singleLine: Boolean,
    minLines: Int = 1,
    icon: MDSTextFieldIcons? = null,
    onIconClick: (() -> Unit) = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    MDSBaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        title = AnnotatedString(text = title),
        placeholder = placeholder,
        isFilled = isFilled,
        isError = isError,
        helperText = helperText,
        maxCount = maxCount,
        singleLine = singleLine,
        minLines = minLines,
        icon = icon,
        onIconClick = onIconClick,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun MDSTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    title: AnnotatedString,
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
        minLines = minLines,
        icon = icon,
        onIconClick = onIconClick,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Preview
@Composable
fun MDSTextFieldPreview() {
    val focusManager = LocalFocusManager.current

    fun validate(text: String, maxCount: Int): Boolean {
        return text.length > maxCount
    }

    val maxCount = 5
    var userInput by remember { mutableStateOf(TextFieldValue()) }
    var isFilled by remember { mutableStateOf(false) }
    val isError by remember { derivedStateOf { validate(userInput.text, maxCount) } }

    MDSTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFilled = !it.isFocused },
        value = userInput,
        onValueChange = { userInput = it },
        title = buildAnnotatedString {
            append("title")
            withStyle(style = SpanStyle(color = Red03)) {
                append("*")
            }
        },
        placeholder = "placeholder",
        isFilled = isFilled,
        isError = isError,
        helperText = "글자수는 ${maxCount}자 이하로 입력해주세요.",
        maxCount = maxCount,
        singleLine = true,
        icon = MDSTextFieldIcons.Clear,
        onIconClick = { userInput = userInput.copy("") },
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}

@Preview
@Composable
fun MDSTextFieldPreviewForSearch() {
    val focusManager = LocalFocusManager.current

    var userInput by remember { mutableStateOf(TextFieldValue()) }
    var isFilled by remember { mutableStateOf(false) }

    MDSTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFilled = !it.isFocused },
        value = userInput,
        onValueChange = { userInput = it },
        title = "title",
        placeholder = "placeholder",
        isFilled = isFilled,
        singleLine = true,
        icon = MDSTextFieldIcons.Search,
        onIconClick = { },
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}
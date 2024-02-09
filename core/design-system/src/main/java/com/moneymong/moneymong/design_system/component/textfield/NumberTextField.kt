package com.moneymong.moneymong.design_system.component.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.component.textfield.util.PriceType
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.DateVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.PriceVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.TimeVisualTransformation


@Composable
fun MDSNumberTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    title: String,
    placeholder: String,
    isFilled: Boolean,
    onIconClick: (() -> Unit),
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    MDSBaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = { original ->
            onValueChange(original.copy(text = original.text.filter { char -> char.isDigit() }))
        },
        title = title,
        placeholder = placeholder,
        isFilled = isFilled,
        singleLine = singleLine,
        icon = MDSTextFieldIcons.Clear,
        onIconClick = onIconClick,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
    )
}

@Preview
@Composable
fun MDSPriceTextFieldPreview() {
    val focusManager = LocalFocusManager.current

    var userInput by remember { mutableStateOf(TextFieldValue()) }
    var isFilled by remember { mutableStateOf(false) }
    var dealType by remember { mutableStateOf(PriceType.None) }

    Column {
        MDSNumberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFilled = !it.isFocused },
            value = userInput,
            onValueChange = {
                userInput = it.copy(text = it.text.trimStart { char -> char == '0' })
            },
            title = "금액",
            placeholder = "거래 금액을 입력해주세요",
            isFilled = isFilled,
            onIconClick = { userInput = userInput.copy("") },
            visualTransformation = PriceVisualTransformation(type = dealType),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MDSButton(
                modifier = Modifier.weight(1f),
                onClick = { dealType = PriceType.Income },
                text = "수입"
            )
            MDSButton(
                modifier = Modifier.weight(1f),
                onClick = { dealType = PriceType.Expense },
                text = "지출"
            )
        }
    }
}

@Preview
@Composable
fun MDSDateTextFieldPreview() {
    val focusManager = LocalFocusManager.current

    var userInput by remember { mutableStateOf(TextFieldValue()) }
    var isFilled by remember { mutableStateOf(false) }

    MDSNumberTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFilled = !it.isFocused },
        value = userInput,
        onValueChange = { if (it.text.length <= 8) userInput = it.copy(text = it.text) },
        title = "날짜",
        placeholder = "YYYY/MM/DD",
        isFilled = isFilled,
        onIconClick = { userInput = userInput.copy("") },
        visualTransformation = DateVisualTransformation(),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}

@Preview
@Composable
fun MDSTimeTextFieldPreview() {
    val focusManager = LocalFocusManager.current

    var userInput by remember { mutableStateOf(TextFieldValue()) }
    var isFilled by remember { mutableStateOf(false) }

    MDSNumberTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFilled = !it.isFocused },
        value = userInput,
        onValueChange = { if (it.text.length <= 6) userInput = it.copy(text = it.text) },
        title = "시간",
        placeholder = "00:00:00 (24시 단위)",
        isFilled = isFilled,
        onIconClick = { userInput = userInput.copy("") },
        visualTransformation = TimeVisualTransformation(),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}
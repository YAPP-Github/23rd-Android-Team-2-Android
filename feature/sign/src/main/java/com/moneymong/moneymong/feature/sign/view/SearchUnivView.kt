package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.University
import com.moneymong.moneymong.feature.sign.item.UnivItem
import com.moneymong.moneymong.feature.sign.mockUniversities

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchUnivView(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onChange: (TextFieldValue) -> Unit,
    value: TextFieldValue
) {
    var tvalue by remember { mutableStateOf(value) }
    var filteredUniversities by remember { mutableStateOf(mockUniversities) }
    var isListVisible by remember { mutableStateOf(false) }
    var isFilled by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current


    fun filterUniversities(query: String) {
        filteredUniversities = if (query.isEmpty()) {
            mockUniversities
        } else {
            mockUniversities.filter { it.univ.contains(query, ignoreCase = true) }
        }

    }


    Column(
        modifier = modifier.background(White)
    ) {
        MDSTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tvalue,
            onValueChange = {
                onChange(it)
                tvalue = it
                filterUniversities(it.text)
                isListVisible = it.text.isNotEmpty()
                isFilled = false
            },
            title = "대학교",
            placeholder = "ex) 머니대학교",
            isFilled = isFilled,
            isError = false,
            maxCount = null,
            singleLine = true,
            icon = MDSTextFieldIcons.Search,
            onIconClick = {
                isListVisible = if (tvalue.text.isEmpty()) {
                    false
                } else {
                    filterUniversities(tvalue.text)
                    isFilled = true
                    true
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    isFilled = true
                    keyboardController?.hide()
                }
            )
        )

        if (isListVisible) {
            if (filteredUniversities.isNotEmpty()) {
                UnivList(univs = filteredUniversities, onClick)
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp)
                ) {

                    Text(
                        text = "검색결과가 없습니다",
                        style = Body4,
                        color = Gray05
                    )
                }

            }
        }
    }
}


@Composable
fun UnivList(univs: List<University>, onClick: (String) -> Unit) {
    LazyColumn {
        items(univs) { univ ->
            UnivItem(
                univs = univ,
                onClick = onClick
            )
        }
    }
}


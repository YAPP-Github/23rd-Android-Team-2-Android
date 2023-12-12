package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.University
import com.moneymong.moneymong.feature.sign.item.UnivItem
import com.moneymong.moneymong.feature.sign.mockUniversities

@Composable
fun SearchUnivView(modifier: Modifier = Modifier) {
    var textValue by remember { mutableStateOf(TextFieldValue()) }
    var filteredUniversities by remember { mutableStateOf(mockUniversities) }
    var isListVisible by remember { mutableStateOf(false) }
    var isFilled by remember { mutableStateOf(false) }


    fun filterUniversities(query: String) {
        filteredUniversities = if (query.isEmpty()) {
            mockUniversities
        } else {
            mockUniversities.filter { it.univ.contains(query, ignoreCase = true) }
        }

    }

    Column(
        modifier = Modifier.background(White)
    ) {
        MDSTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = {
                textValue = it
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
                isListVisible = if(textValue.text.isEmpty())
                {
                    false
                } else {
                    filterUniversities(textValue.text)
                    isFilled = true
                    true
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions.Default

//                (
//                onDone = {
//                    isFilled = true
//
//                }
//            )
        )

        if (isListVisible) {
            UnivList(univs = filteredUniversities)
        }


    }
}


@Composable
fun UnivList(univs: List<University>) {
    LazyColumn {
        items(univs) { user ->
            UnivItem(univs = user)
        }
    }
}



@Preview
@Composable
fun UnivListPreview() {
    UnivList(univs = mockUniversities)
}

@Preview
@Composable
fun preview(){
    SearchUnivView(modifier= Modifier.padding(0.dp, 40.dp, 0.dp, 0.dp))
}


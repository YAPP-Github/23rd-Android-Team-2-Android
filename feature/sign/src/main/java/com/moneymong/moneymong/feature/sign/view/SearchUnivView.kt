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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.signup.UniversitiesEntity
import com.moneymong.moneymong.domain.entity.signup.University
import com.moneymong.moneymong.feature.sign.item.UnivItem
import com.moneymong.moneymong.feature.sign.viewmodel.SignUpViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchUnivView(
    modifier: Modifier = Modifier,
    isFilled: Boolean,
    isFilledChanged: (Boolean) -> Unit,
    isListVisible: Boolean,
    isListVisibleChanged: (Boolean) -> Unit,
    isItemSelectedChanged: (Boolean) -> Unit,
    onClick: (String) -> Unit,
    onChange: (TextFieldValue) -> Unit,
    onSearchIconClicked: (String) -> Unit,
    isItemSelected: Boolean,
    textValue: TextFieldValue,
    universityResponse: UniversitiesEntity?,
    value: TextFieldValue
) {

    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.background(White)
    ) {
        MDSTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = {
                onChange(it)
                job?.cancel() // 이전에 실행된 Job 취소
                job = scope.launch {
                    delay(300)
                    onSearchIconClicked(it.text)
                }
                isListVisibleChanged(it.text.isNotEmpty())
                isFilledChanged(false)
            },
            title = "대학교",
            placeholder = "ex) 머니대학교",
            isFilled = isFilled,
            isError = false,
            maxCount = null,
            singleLine = true,
            icon = MDSTextFieldIcons.Search,
            onIconClick = {
                if (value.text.isEmpty()) {
                    isListVisibleChanged(false)
                } else {
                    onSearchIconClicked(textValue.toString())
                    isFilledChanged(true)
                    isListVisibleChanged(true)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    isFilledChanged(true)
                    keyboardController?.hide()
                }
            )
        )

        if (isListVisible) {
            if (universityResponse?.universities?.isNotEmpty() == true) {
                UnivList(
                    isItemSelected = isItemSelected,
                    isItemSelectedChanged = isItemSelectedChanged ,
                    univs = universityResponse.universities,
                    onClick = onClick
                )
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
fun UnivList(
    isItemSelected: Boolean,
    isItemSelectedChanged: (Boolean) -> Unit,
    univs: List<University>,
    onClick: (String) -> Unit
) {
    LazyColumn {
        items(univs) { univ ->
            UnivItem(
                isItemSelected = isItemSelected,
                isItemSelectedChanged = isItemSelectedChanged,
                univs = univ,
                onClick = onClick
            )
        }
    }
}


package com.moneymong.moneymong.feature.agency.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.component.selection.MDSSelection
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading2
import com.moneymong.moneymong.feature.agency.search.AgencyType

@Composable
internal fun AgencyResisterContentView(
    modifier: Modifier = Modifier,
    agencyType: AgencyType?,
    onAgencyTypeChange: (AgencyType) -> Unit,
    agencyName: TextFieldValue,
    onAgencyNameChange: (TextFieldValue) -> Unit,
    changeNameTextFieldIsError: (Boolean) -> Unit
) {
    Column(modifier = modifier) {
        TitleView()
        Spacer(modifier = Modifier.height(44.dp))
        SelectTypeView(
            agencyType = agencyType,
            onAgencyTypeChange = onAgencyTypeChange
        )
        Spacer(modifier = Modifier.height(24.dp))
        InputNameView(
            agencyName = agencyName,
            onAgencyNameChange = onAgencyNameChange,
            changeIsError = changeNameTextFieldIsError
        )
    }
}


@Composable
private fun TitleView() {
    Text(
        text = "동아리 or 학생회 등록에\n필요한 항목들을 채워주세요.",
        color = Gray10,
        style = Heading2
    )
}


@Composable
private fun SelectTypeView(
    agencyType: AgencyType?,
    onAgencyTypeChange: (AgencyType) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "소속 유형",
            color = Gray06,
            style = Body2
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MDSSelection(
                modifier = Modifier.weight(1f),
                text = "동아리",
                isSelected = agencyType == AgencyType.CLUB,
                onClick = { onAgencyTypeChange(AgencyType.CLUB) }
            )
            MDSSelection(
                modifier = Modifier.weight(1f),
                text = "학생회",
                isSelected = agencyType == AgencyType.COUNCIL,
                onClick = { onAgencyTypeChange(AgencyType.COUNCIL) }
            )
        }
    }
}


@Composable
private fun InputNameView(
    agencyName: TextFieldValue,
    onAgencyNameChange: (TextFieldValue) -> Unit,
    changeIsError: (Boolean) -> Unit
) {
    fun validate(text: String, maxCount: Int) = text.length > maxCount

    val agencyNameValue by rememberUpdatedState(newValue = agencyName)
    val maxCount = 20
    var isFilled by remember { mutableStateOf(false) }
    val isError by remember {
        derivedStateOf {
            validate(
                text = agencyNameValue.text,
                maxCount = maxCount
            )
        }
    }

    LaunchedEffect(key1 = isError) {
        changeIsError(isError)
    }

    val focusManager = LocalFocusManager.current
    fun filterText(text: String) = text.filter { it.isLetterOrDigit() || it == ' ' }

    MDSTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFilled = !it.isFocused },
        value = agencyNameValue,
        onValueChange = { onAgencyNameChange(it.copy(text = filterText(it.text))) },
        title = "소속 이름",
        placeholder = "소속 이름을 입력해주세요",
        isFilled = isFilled,
        isError = isError,
        helperText = "${maxCount}자 이하로 입력해주세요",
        maxCount = maxCount,
        singleLine = true,
        icon = MDSTextFieldIcons.Clear,
        onIconClick = { onAgencyNameChange(agencyName.copy(text = "")) },
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )
}
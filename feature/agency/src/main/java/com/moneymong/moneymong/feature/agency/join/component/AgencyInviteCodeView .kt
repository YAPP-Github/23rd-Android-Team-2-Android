package com.moneymong.moneymong.feature.agency.join.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.Red02


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AgencyInviteCodeView(
    numbers: MutableList<String>,
    focusRequesters: List<FocusRequester>,
    onIsErrorChanged: (Boolean) -> Unit,
    onIsNumbersChanged: (Int, String) -> Unit,
    isError: Boolean,
    compareError: Boolean,
) {
    val allNumbersEntered = numbers.none { it.isEmpty() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    // 모든 숫자가 입력되었고, 조건을 만족하지 않는 경우 isError를 true로 설정
    LaunchedEffect(key1 = allNumbersEntered) {
        onIsErrorChanged(compareError)
    }

    //포커스를 첫번째 textField로 설정
    LaunchedEffect(key1 = Unit) {
        focusRequesters[0].requestFocus()
    }

    Column(
        modifier = Modifier
            .background(White)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            text = "초대코드",
            color = if (!isError) Blue04 else Red02,
            style = Body2
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(top = 8.dp)
        ) {
            numbers.forEachIndexed { index, value ->
                val visibleCode = value.isEmpty() || compareError

                Row(
                    modifier = Modifier
                        .weight(1f) //Todo - 디자인 가로 사이즈 변경
                        .height(72.dp),
                    //horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (visibleCode) {
                        OutlinedTextField(
                            value = value,
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) {
                                    onIsNumbersChanged(index, newValue)
                                    if (newValue.isNotEmpty() && index < numbers.size - 1) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else {
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .background(if (isError) Gray01 else White)
                                .weight(1f) //Todo - 디자인 가로 사이즈 변경
                                .height(72.dp)
                                .focusRequester(focusRequesters[index]),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            textStyle = Heading1.copy(textAlign = TextAlign.Center),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = if (isError) Red02 else Blue04,
                                unfocusedBorderColor = if (isError) Red02 else Gray03
                            ),
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .weight(1f) //Todo - 디자인 가로 사이즈 변경
                                .height(72.dp),
                            painter = painterResource(id = R.drawable.img_code),
                            contentDescription = null
                        )
                    }
                }
                if (index < numbers.size - 1) {
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}


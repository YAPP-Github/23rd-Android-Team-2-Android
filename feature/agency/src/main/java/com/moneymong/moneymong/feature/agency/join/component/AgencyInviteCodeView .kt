package com.moneymong.moneymong.feature.agency.join.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.Red02
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.join.AgencyJoinState
import com.moneymong.moneymong.feature.agency.join.AgencyJoinViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AgencyInviteCodeView(
    agencyId : Long,
    focusRequesters: List<FocusRequester>,
    isError : Boolean,
    numbers : List<String>,
    agencyCodeNumbers : (Long) -> Unit,
    onIsErrorChanged: (Boolean) -> Unit,
    onIsNumbersChanged: (Int, String) -> Unit,
) {


    val allNumbersEntered = numbers.none { it.isEmpty() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    // 모든 숫자가 입력되었고, 조건을 만족하지 않는 경우 isError를 true로 설정
    LaunchedEffect(key1 = allNumbersEntered) {
        onIsErrorChanged(isError)
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
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            numbers.forEachIndexed { index, value ->
                val visibleCode = value.isEmpty() || isError

                if (visibleCode) {
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            if (newValue.length == 1 ) {
                                onIsNumbersChanged(index, newValue)
                                if (index == numbers.lastIndex) {
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                    agencyCodeNumbers(agencyId)
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(180 / 288f)
                            .background(if (isError) Gray01 else White)
                            .focusRequester(focusRequesters[index]),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        textStyle = Heading1.copy(textAlign = TextAlign.Center),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (isError) Red02 else Blue04,
                            unfocusedBorderColor = if (isError) Red02 else Gray03
                        ),
                    )
                } else {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(id = R.drawable.img_code),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


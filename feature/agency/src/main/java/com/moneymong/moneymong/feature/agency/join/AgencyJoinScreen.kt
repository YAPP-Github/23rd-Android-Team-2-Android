package com.moneymong.moneymong.feature.agency.join

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.snackbar.MDSSnackbarHost
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading3
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.join.component.AgencyCodeView


@Composable
fun AgencyJoinScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(MMHorizontalSpacing)
            .background(White),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .height(44.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    painter = painterResource(id = R.drawable.ic_close_default),
                    contentDescription = null,
                    tint = Black
                )
            }
        },
        content = { innerPadding ->
            JoinContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Composable
private fun JoinContent(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val numbers = remember { mutableStateListOf("", "", "", "", "", "") }
    val allNumbersEntered = numbers.none { it.isEmpty() }
    var isError = allNumbersEntered && numbers.joinToString("") != "000000"
    val focusRequesters = List(6) { FocusRequester() }

    // isError 상태 업데이트
    val onIsErrorChanged = { newIsError : Boolean ->
        isError = newIsError
    }

    //textField 입력되면 값 업데이트
    val onIsNumbersChanged = { index : Int , value : String ->
        numbers[index] = value
    }

    //초대 코드와 입력된 값 비교
    fun CompareError(): Boolean {
        return isError
    }


    LaunchedEffect(key1 = isError) {
        if(isError){
            val result = snackbarHostState.showSnackbar(
                message = "잘못된 초대코드입니다.",
                actionLabel = "다시입력"
            )

            if (result == SnackbarResult.ActionPerformed) {
                numbers.replaceAll { "" }
                focusRequesters[0].requestFocus()
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Yapp에서 받은\n초대코드를 입력해주세요",
            color = Gray10 ,
            style = Heading3
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 151.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            AgencyCodeView(
                numbers = numbers,
                focusRequesters = focusRequesters,
                onIsErrorChanged = onIsErrorChanged,
                isError = isError,
                onIsNumbersChanged = onIsNumbersChanged,
                compareError = CompareError()
            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            MDSSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(BottomCenter)
            )
        }

    }

}

@Preview
@Composable
fun AgencyJoinPreview() {
    AgencyJoinScreen()
}
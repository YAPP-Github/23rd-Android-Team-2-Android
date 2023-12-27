package com.moneymong.moneymong.feature.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.sign.view.SearchUnivView
import com.moneymong.moneymong.feature.sign.view.SignCompleteCheckedView
import com.moneymong.moneymong.feature.sign.view.SignUpButtonView
import com.moneymong.moneymong.feature.sign.view.SignUpGradeView
import com.moneymong.moneymong.feature.sign.view.SignUpTitleView

@Composable
fun SignUpScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(MMHorizontalSpacing),
        topBar = {
            Row(
                modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .background(White)
                            .clickable { },
                        tint = Gray07
                    )
            }
        },
        content = { innerPadding ->
            SignUpContent(modifier = Modifier.padding(innerPadding))
        }
    )
}


@Composable
fun SignUpContent (modifier : Modifier = Modifier ){
    //대학교 선택 상태
    var isSelected by remember{ mutableStateOf(false) }
    //선택한 대학 정보
    var selectedUniv by remember { mutableStateOf("") }
    //가입하기 Button 상태
    var isEnabled by remember { mutableStateOf(false) }
    //대학교 검색 정보
    var textValue by remember { mutableStateOf(TextFieldValue()) }
    //subtitle enabled 상태
    var subTitleState by remember { mutableStateOf(false) }
    //선택한 학년 정보
    var gradeInfor by remember { mutableStateOf("") }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            SignUpTitleView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                subTitleState = subTitleState
            )

            Box(
                modifier= Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
            ){
                if(!isSelected){
                    SearchUnivView(
                        onClick = {
                            isSelected = !isSelected
                            selectedUniv = it
                        },
                        onChange = {textValue = it},
                        value = textValue
                    )
                } else {
                    Column(modifier = Modifier.fillMaxSize()) {
                        SignCompleteCheckedView(
                            modifier = Modifier.fillMaxWidth(),
                            text = selectedUniv,
                            onChanged = { isSelected = !isSelected
                                isEnabled = false
                            })
                        SignUpGradeView(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { isEnabled = true  },
                            gradeInfor = { gradeInfor = it }
                        )
                        }
                    }
                }
            }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            SignUpButtonView(
                modifier = Modifier.fillMaxWidth(),
                isEnabled = isEnabled
            )
        }
    }
}



@Preview
@Composable
fun Preview(){
    SignUpScreen()
}
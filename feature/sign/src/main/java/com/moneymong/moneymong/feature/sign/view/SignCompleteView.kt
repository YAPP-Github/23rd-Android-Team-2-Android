package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.White
@Composable
fun SignCompleteView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_congrats2),
            contentDescription = null,
            modifier = Modifier
                .background(White)
                .fillMaxWidth()
                .size(100.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
        ) {
            Text(
                text = "회원가입을 축하합니다",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(top = 12.dp),
                textAlign = TextAlign.Center,
                style = Heading1,
                color = Gray10
            )

            Text(
                text = "머니몽을 자유롭게 사용해보세요 ",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                style = Body4,
                color = Gray07
            )

        }
    }
}


@Preview
@Composable
fun SignCompleteViewPreview(){
    SignCompleteView()
}
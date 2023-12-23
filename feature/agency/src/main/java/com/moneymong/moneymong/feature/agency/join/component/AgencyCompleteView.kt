package com.moneymong.moneymong.feature.agency.join.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
fun AgencyCompleteView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(White)
            .padding(top = 207.dp, bottom = 208.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .background(White)
                .size(100.dp),
            painter = painterResource(id = R.drawable.img_complete),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(top = 12.dp),
            text = "가입을 축하합니다!",
            style = Heading1,
            color = Gray10,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(top = 4.dp),
            text = "이제 동아리 가계부를 확인할 수 있어요",
            style = Body4,
            color = Gray07,
            textAlign = TextAlign.Center
        )

    }
}


@Preview
@Composable
fun SignCompleteViewPreview() {
    AgencyCompleteView()
}
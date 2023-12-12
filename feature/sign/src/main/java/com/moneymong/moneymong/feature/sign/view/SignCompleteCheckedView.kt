package com.moneymong.moneymong.feature.sign.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.theme.Black
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun SignCompleteCheckedView(modifier: Modifier = Modifier , text: String, onChanged : () -> Unit) {
    Row(
        modifier = modifier
            .background(White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_university),
            modifier = Modifier.size(22.dp),
            contentDescription = null
        )
        Text(
            text = text,
            color = Black,
            style = Body4,
            modifier = Modifier
                .weight(1f)
                .height(22.dp)
                .padding(10.dp, 0.dp, 0.dp, 0.dp)
        )

        IconButton(
            onClick = { onChanged() },
            modifier = Modifier.size(24.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_pencil),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                tint = Gray03
            )
        }
    }
}

//@Preview
//@Composable
//fun SignCompleteCheckedViewPre(){
//    SignCompleteCheckedView( text = "홍익대학교 서울캠퍼스 ")
//}
package com.example.member.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.member.User
import com.example.member.util.MemberRoundRectShadow
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.tag.MDSTag
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MemberCardView(
    modifier : Modifier = Modifier,
    onChange : ()-> Unit
) {
    val user = User("김세현", 2)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .MemberRoundRectShadow()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    modifier = Modifier.size(29.dp),
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = user.name,
                    style = Body4,
                    color = Gray10
                )

                MDSTag(
                    modifier = Modifier.padding(start = 6.dp),
                    text = if(user.type == 1) "일반멤버" else "운영진",
                    backgroundColor = if(user.type == 1) Mint03 else Blue04,
                    contentColor = White,
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp),
                thickness = 1.dp,
                color = Gray02
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clickable {
                        onChange()
                    },
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "동아리 초대 코드 복사하기",
                    style = Body3,
                    color = Blue04
                )

                Icon(
                    modifier = Modifier.padding(start = 6.dp),
                    painter = painterResource(id = R.drawable.ic_invite),
                    contentDescription = null,
                    tint = Blue04
                )

            }

        }
    }
}
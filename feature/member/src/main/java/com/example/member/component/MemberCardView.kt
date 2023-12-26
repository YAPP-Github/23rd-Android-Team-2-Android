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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.member.User
import com.example.member.UserType
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
    onCopyChange : () -> Unit,
    onReissueChange : () -> Unit
) {
    val user = User("김세현", UserType.ADMINISTRATOR)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .MemberRoundRectShadow()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    modifier = Modifier.size(44.dp),
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
                    text = if(user.type == UserType.GENERAL_MEMBER) "일반멤버" else "운영진",
                    backgroundColor = if(user.type == UserType.GENERAL_MEMBER) Mint03 else Blue04,
                    contentColor = White,
                )

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                thickness = 1.dp,
                color = Gray02
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "초대코드 123456",
                    style = Body3,
                    color = Gray10
                )
                Row(
                    modifier = Modifier
                        .padding(start = 63.dp)
                        .clickable {
                            onCopyChange()
                        },
                ){
                    Text(
                        text = "복사",
                        style = Body3,
                        color = Blue04
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_copy),
                        modifier = Modifier
                            .padding(start = 2.dp, top = 1.dp, bottom = 1.dp)
                            .size(18.dp),
                        contentDescription = null,
                        tint = Blue04
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            onReissueChange()
                        }

                ){
                    Text(
                        text = "재발급",
                        style = Body3,
                        color = Blue04
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_reissue),
                        modifier = Modifier
                            .padding(start = 2.dp, top = 1.dp, bottom = 1.dp)
                            .size(18.dp),
                        contentDescription = null,
                        tint = Blue04

                    )
                }

            }

        }
    }
}

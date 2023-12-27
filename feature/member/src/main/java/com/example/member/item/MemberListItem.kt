package com.example.member.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.member.Member
import com.example.member.MemberType
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.tag.MDSTag
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MemberListItem(member: Member, onIconClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(44.dp),
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = member.name,
            style = Body4,
            color = Gray10
        )

        MDSTag(
            modifier = Modifier.padding(start = 6.dp),
            text = if (member.type == MemberType.GENERAL_MEMBER) "일반 멤버" else "운영진",
            backgroundColor = if (member.type == MemberType.GENERAL_MEMBER) Mint03 else Blue04,
            contentColor = White,
        )

        Spacer(Modifier.weight(1f))

        Icon(
            modifier = Modifier.clickable {
                onIconClick()
            },
            painter = painterResource(id = R.drawable.ic_more_vert),
            contentDescription = null,
            tint = Gray05
        )
    }

}


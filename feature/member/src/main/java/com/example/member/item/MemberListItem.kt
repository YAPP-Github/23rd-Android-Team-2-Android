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
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.tag.MDSTag
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity

@Composable
fun MemberListItem(
    agencyUser: AgencyUserEntity,
    userId : Long,
    onIconClick: () -> Unit,
    memberMyInfo: AgencyUserEntity,
    vertClickedUserIdChanged: (Long) -> Unit,
) {
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
            text = agencyUser.nickname,
            style = Body4,
            color = Gray10
        )

        MDSTag(
            modifier = Modifier.padding(start = 6.dp),
            text = if (agencyUser.agencyUserRole == "MEMBER") "일반 멤버" else "운영진",
            backgroundColor = if (agencyUser.agencyUserRole == "MEMBER") Mint03 else Blue04,
            contentColor = White,
        )

        Spacer(Modifier.weight(1f))

        if (memberMyInfo.agencyUserRole == "STAFF"){
            Icon(
                modifier = Modifier.noRippleClickable {
                    onIconClick()
                    vertClickedUserIdChanged(userId)
                },
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = null,
                tint = Gray05
            )

        }
    }
}


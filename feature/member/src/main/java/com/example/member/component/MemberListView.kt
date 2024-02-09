package com.example.member.component

import android.util.Log
import com.moneymong.moneymong.design_system.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.member.item.MemberListItem
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.member.AgencyUserEntity

@Composable
fun MemberListView(
    modifier: Modifier = Modifier,
    memberMyInfo: AgencyUserEntity,
    filteredMemberList: List<AgencyUserEntity>,
    onIconClick: (Boolean) -> Unit,
    updateFilteredMemberList: (Long) -> Unit,
    vertClickedUserIdChanged: (Long) -> Unit,
) {
    updateFilteredMemberList(memberMyInfo.userId)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Text(
            text = "멤버 목록",
            style = Body3,
            color = Gray07
        )
        if (filteredMemberList.isEmpty()) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 93.dp),
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = painterResource(id = R.drawable.img_club),
                    contentDescription = null
                )

                Text(
                    text = "아직 멤버가 없습니다",
                    modifier = Modifier
                        .padding(top = 4.dp),
                    style = Body3,
                    color = Gray06
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = filteredMemberList,
                    itemContent = { filteredMemberList ->
                        if (filteredMemberList.userId != memberMyInfo.userId) {
                            MemberListItem(
                                filteredMemberList,
                                onIconClick = {
                                    onIconClick(true)
                                },
                                memberMyInfo = memberMyInfo,
                                userId = filteredMemberList.userId,
                                vertClickedUserIdChanged = vertClickedUserIdChanged
                            )
                        }
                    }
                )
            }
        }
    }

}

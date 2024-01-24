package com.example.member.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.member.Member
import com.example.member.MemberType
import com.example.member.item.MemberListItem
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray07
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MemberListView(
    modifier: Modifier = Modifier,
    onIconClick: (Boolean) -> Unit
) {
    val memberList = listOf(
        Member(1, "김도하", MemberType.GENERAL_MEMBER),
        Member(2, "구나연", MemberType.ADMINISTRATOR),
        Member(3, "기은서", MemberType.GENERAL_MEMBER),
        Member(4, "김기서", MemberType.GENERAL_MEMBER),
        Member(5, "안병헌", MemberType.GENERAL_MEMBER),
        Member(6, "장희직", MemberType.GENERAL_MEMBER)
    )
    val members = remember { memberList }

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

        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = members,
                itemContent = { MemberListItem(it, onIconClick = { onIconClick(true) }) }
            )
        }
    }
}

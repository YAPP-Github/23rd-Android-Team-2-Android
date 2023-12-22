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
import com.example.member.item.MemberListItem
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.White

@Composable
fun MemberListView(modifier : Modifier = Modifier, onIconClick : ()-> Unit) {
    val memberList = listOf(
        Member(1, "김도하", 1),
        Member(2, "구나연", 2),
        Member(3, "기은서", 1),
        Member(4, "김기서", 1),
        Member(5, "안병헌", 1),
        Member(6, "장희직", 1)
    )
    val members = remember { memberList }

    Column(
        modifier = modifier
        .fillMaxWidth()
        .background(White)
        .background(White)
    ){
        Text(
            text = "멤버 목록",
            style = Body3,
            color = Color(0xFF37404F)
        )

        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = members,
                itemContent = { MemberListItem(it, onIconClick = { onIconClick() } ) }
            )
        }
    }
}


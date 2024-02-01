package com.moneymong.moneymong.feature.agency.search.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.tag.MDSTag
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray02
import com.moneymong.moneymong.design_system.theme.Gray05
import com.moneymong.moneymong.design_system.theme.Gray09
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.search.Agency
import com.moneymong.moneymong.feature.agency.search.AgencyType

@Composable
fun AgencyItem(
    modifier: Modifier = Modifier,
    agency: Agency,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = White)
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = Gray02,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AgencyItemImage()
        AgencyItemInfo(
            name = agency.name,
            type = agency.type,
            memberCount = agency.memberCount
        )
    }
}

@Composable
fun AgencyItemImage(modifier: Modifier = Modifier) {
    Image(
        contentDescription = "agency image",
        modifier = modifier.size(48.dp),
        painter = painterResource(id = R.drawable.img_club_filled),
    )
}

@Composable
fun AgencyItemInfo(name: String, type: AgencyType, memberCount: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.weight(weight = 1f, fill = false),
                text = name,
                color = Gray09,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = Body3
            )
            MDSTag(
                text = type.text,
                backgroundColor = Blue01,
                contentColor = Blue04
            )
        }
        Text(
            text = "멤버수 $memberCount",
            color = Gray05,
            style = Body2
        )
    }
}
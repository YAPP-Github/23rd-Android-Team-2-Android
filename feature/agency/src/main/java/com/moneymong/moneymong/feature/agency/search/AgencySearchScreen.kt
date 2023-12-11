package com.moneymong.moneymong.feature.agency.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSFloatingActionButton
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.feature.agency.Agency
import com.moneymong.moneymong.feature.agency.search.component.AgencySearchTopBar

@Composable
fun AgencySearchScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AgencySearchTopBar()
            AgencySearchContentView(
                modifier = Modifier.weight(1f),
                agencies = emptyList()
            )
        }
        MDSFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp),
            onClick = { }, // todo: navigate to 소속 등록
            iconResource = R.drawable.ic_plus_default,
            containerColor = Red03
        )
    }
}

@Composable
private fun AgencySearchContentView(
    modifier: Modifier = Modifier,
    agencies: List<Agency>
) {
    if (agencies.isEmpty()) {
        ContentViewWithoutAgencies(modifier = modifier)
    } else {
        ContentViewWithAgencies(modifier = modifier, agencies = agencies)
    }
}


@Composable
private fun ContentViewWithAgencies(
    modifier: Modifier = Modifier,
    agencies: List<Agency>
) {
}

@Composable
private fun ContentViewWithoutAgencies(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(size = 80.dp),
            painter = painterResource(id = R.drawable.img_agency),
            contentDescription = "agency image",
        )
        Text(
            text = "아직 등록된 소속이 없어요\n" +
                    "하단 버튼을 통해 등록해보세요",
            textAlign = TextAlign.Center,
            color = Gray08,
            style = Body4
        )
    }
}
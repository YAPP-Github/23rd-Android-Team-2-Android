package com.moneymong.moneymong.feature.agency.register.complete

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.Heading1
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AgencyRegisterCompleteScreen(
    modifier: Modifier = Modifier,
    viewModel: AgencyRegisterCompleteViewModel = hiltViewModel(),
    navigateToSearch: () -> Unit,
    navigateToLedger: () -> Unit,
    navigateToLedgerManual: () -> Unit
) {

    viewModel.collectSideEffect {
        when (it) {
            is AgencyRegisterCompleteSideEffect.NavigateToAgencySearch -> navigateToSearch()
            is AgencyRegisterCompleteSideEffect.NavigateToLedger -> navigateToLedger()
            is AgencyRegisterCompleteSideEffect.NavigateToLedgerManual -> navigateToLedgerManual()
        }
    }

    BackHandler { viewModel.navigateToAgencySearch() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Gray08)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        TitleView(onBackClick = viewModel::navigateToAgencySearch)
        ContentView(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        )
        MDSButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = viewModel::navigateToLedger,
            text = "소속 장부 확인하러 가기",
            type = MDSButtonType.SECONDARY
        )
        Spacer(modifier = Modifier.height(12.dp))
        MDSButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            onClick = viewModel::navigateToLedgerManual,
            text = "동아리 운영비 등록하러 가기"
        )
    }
}

@Composable
private fun TitleView(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 16.dp),
            text = "등록완료",
            color = White,
            style = Heading1
        )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(vertical = 10.dp)
                .size(24.dp)
                .clickable { onBackClick() },
            painter = painterResource(id = R.drawable.ic_close_default),
            tint = White,
            contentDescription = null
        )
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(width = 188.dp, height = 100.dp),
            painter = painterResource(id = R.drawable.img_complete_agency),
            contentDescription = "소속 등록 완료"
        )
        Text(
            text = "등록해주셔서 감사합니다",
            style = Heading1,
            color = White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "내가 등록한 소속을 확인해보세요",
            style = Body4,
            color = Gray03
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AgencyRegisterCompleteScreenPreview() {
    AgencyRegisterCompleteScreen(
        navigateToSearch = {},
        navigateToLedger = {},
        navigateToLedgerManual = {}
    )
}
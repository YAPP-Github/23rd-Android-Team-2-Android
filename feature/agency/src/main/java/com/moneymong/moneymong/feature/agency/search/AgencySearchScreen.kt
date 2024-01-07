package com.moneymong.moneymong.feature.agency.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.bottomSheet.MDSBottomSheet
import com.moneymong.moneymong.design_system.component.button.MDSFloatingActionButton
import com.moneymong.moneymong.design_system.component.tooltip.MDSToolTip
import com.moneymong.moneymong.design_system.component.tooltip.MDSToolTipPosition
import com.moneymong.moneymong.design_system.theme.Body4
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray08
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.feature.agency.Agency
import com.moneymong.moneymong.feature.agency.search.component.AgencySearchBottomSheetContent
import com.moneymong.moneymong.feature.agency.search.component.AgencySearchTopBar
import com.moneymong.moneymong.feature.agency.search.item.AgencyItem
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencySearchScreen(
    modifier: Modifier = Modifier,
    viewModel: AgencySearchViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit
) {
    val agencies = emptyList<Agency>()
//    val agencies = mockAgencies

    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is AgencySearchSideEffect.NavigateToRegister -> {
                navigateToRegister()
            }
        }
    }

    if (state.visibleBottomSheet) {
        MDSBottomSheet(
            onDismissRequest = viewModel::onDismissBottomSheet,
        ) {
            AgencySearchBottomSheetContent(
                checkedType = state.registerType,
                changeType = { type -> viewModel.changeRegisterType(type) },
                onConfirm = viewModel::onBottomSheetConfirmButtonClicked
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = if (agencies.isEmpty()) White else Gray01)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AgencySearchTopBar()
            AgencySearchContentView(
                modifier = Modifier.weight(1f),
                agencies = agencies
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.End
        ) {
            if (agencies.isEmpty()) {
                MDSToolTip(
                    text = "소속이 없다면 등록해보세요",
                    position = MDSToolTipPosition.Right
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            MDSFloatingActionButton(
                onClick = { viewModel.changeVisibleBottomSheet(true) },
                iconResource = R.drawable.ic_plus_default,
                containerColor = Red03
            )
        }
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
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(items = agencies) {
            AgencyItem(agency = it)
        }
    }
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
            text = "아직 등록된 소속이 없어요\n하단 버튼을 통해 등록해보세요",
            textAlign = TextAlign.Center,
            color = Gray08,
            style = Body4
        )
    }
}
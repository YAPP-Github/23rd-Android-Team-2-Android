package com.moneymong.moneymong.feature.agency.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
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
    navigateToRegister: () -> Unit,
    navigateAgencyJoin: (agencyId: Long) -> Unit
) {
    val state by viewModel.collectAsState()
    val pagingItems = viewModel.agencies.collectAsLazyPagingItems()

    viewModel.collectSideEffect {
        when (it) {
            is AgencySearchSideEffect.NavigateToRegister -> {
                navigateToRegister()
            }

            is AgencySearchSideEffect.NavigateToJoin -> {
                navigateAgencyJoin(it.agencyId)
            }
        }
    }

    if (state.visibleBottomSheet) {
        MDSBottomSheet(
            onDismissRequest = viewModel::onDismissBottomSheet,
        ) {
            AgencySearchBottomSheetContent(
                checkedType = state.registerType,
                changeType = viewModel::changeRegisterType,
                onConfirm = viewModel::onBottomSheetConfirmButtonClicked
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = if (pagingItems.itemCount == 0) White else Gray01)
            .padding(horizontal = MMHorizontalSpacing)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AgencySearchTopBar()
            AgencySearchContentView(
                modifier = Modifier.weight(1f),
                pagingItems = pagingItems,
                onClickItem = viewModel::onAgencyItemClicked
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.End
        ) {
            if (pagingItems.itemCount == 0) {
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
    pagingItems: LazyPagingItems<Agency>,
    onClickItem: (agencyId: Long) -> Unit
) {
    if (pagingItems.itemCount == 0) {
        ContentViewWithoutAgencies(
            modifier = modifier,
            pagingItems = pagingItems
        )
    } else {
        ContentViewWithAgencies(
            modifier = modifier,
            pagingItems = pagingItems,
            onClickItem = onClickItem
        )
    }
}


@Composable
private fun ContentViewWithAgencies(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<Agency>,
    onClickItem: (agencyId: Long) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            count = pagingItems.itemCount, key = pagingItems.itemKey { it.id }
        ) {
            pagingItems[it]?.let { agency ->
                AgencyItem(
                    agency = agency,
                    onClick = { onClickItem(agency.id) }
                )
            }
        }

        when (pagingItems.loadState.source.append) {
            is LoadState.Loading -> {
                // todo: loading item
                item {
                    Box(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                val e = pagingItems.loadState.source.append as LoadState.Error
                // todo: error item
                item {
                    Column(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${e.error.message}")
                        Button(onClick = pagingItems::retry) {
                            Text(text = "retry")
                        }
                    }
                }
            }

            else -> Unit
        }
    }
}

@Composable
private fun ContentViewWithoutAgencies(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<Agency>,
) {

    when (pagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            // todo: loading screen
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            val e = pagingItems.loadState.refresh as LoadState.Error
            // todo: error screen
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${e.error.message}")
                Button(onClick = pagingItems::retry) {
                    Text(text = "retry")
                }
            }
        }

        is LoadState.NotLoading -> {
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
    }
}
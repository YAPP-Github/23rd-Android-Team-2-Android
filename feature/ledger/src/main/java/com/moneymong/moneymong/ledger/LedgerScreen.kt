package com.moneymong.moneymong.ledger

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSFloatingActionButton
import com.moneymong.moneymong.design_system.component.tooltip.MDSToolTip
import com.moneymong.moneymong.design_system.component.tooltip.MDSToolTipPosition
import com.moneymong.moneymong.design_system.theme.Mint02
import com.moneymong.moneymong.design_system.theme.Mint03
import com.moneymong.moneymong.ledger.view.LedgerAdminEmptyView
import com.moneymong.moneymong.ledger.view.LedgerAgencyEmptyView
import com.moneymong.moneymong.ledger.view.LedgerDefaultView
import com.moneymong.moneymong.ledger.view.LedgerGroupSelectBottomSheet
import com.moneymong.moneymong.ledger.view.LedgerMemberEmptyView
import com.moneymong.moneymong.ledger.view.LedgerTabRowView
import com.moneymong.moneymong.ledger.view.LedgerTopbarView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LedgerScreen(
    modifier: Modifier = Modifier
) {
    var expandableFab by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(if (expandableFab) 45f else 0f, label = "")
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    val openSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.show()
        }
    }
    val closeSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    Scaffold(
        topBar = {
            LedgerTopbarView(
                header = "장부",
                icon = R.drawable.ic_chevron_bottom, // TODO
                visibleArrow = true, // TODO 소속이 있을 때만
                onClickDownArrow = openSheet
            )
        }
    ) {
        ModalBottomSheetLayout(
            sheetContent = {
                LedgerGroupSelectBottomSheet(onClickItem = {
                    closeSheet() // TODO
                })
            },
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (false) {// TODO 소속이 없을 경우
                        LedgerAgencyEmptyView(
                            onClickFindTeam = { /* TODO */ }
                        )
                    } else {
                        LedgerTabRowView(selectedTabIndex = remember { mutableStateOf(0) }) // TODO
                        if (false) { // TODO 장부가 없을 경우
                            if (true) { // TODO 멤버일 경우
                                LedgerMemberEmptyView()
                            } else {
                                LedgerAdminEmptyView()
                            }
                        } else {
                            LedgerDefaultView()
                        }
                    }
                }
                if (true) { // TODO 어드민일 경우
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 20.dp, bottom = 20.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        if (true && !expandableFab) { //  TODO 최초 기록 전
                            MDSToolTip(
                                text = "해당 기능을 사용해보세요",
                                position = MDSToolTipPosition.Right
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        AnimatedVisibility(
                            visible = expandableFab,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(
                                    durationMillis = 250,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(
                                    durationMillis = 150,
                                    easing = FastOutLinearInEasing
                                )
                            )
                        ) {
                            MDSFloatingActionButton(
                                iconResource = R.drawable.ic_scan,
                                containerColor = Mint03,
                                onClick = { /*TODO*/ }
                            )
                        }
                        if (expandableFab) Spacer(modifier = Modifier.height(10.dp))
                        AnimatedVisibility(
                            visible = expandableFab,
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(
                                    durationMillis = 150,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { fullHeight -> fullHeight },
                                animationSpec = tween(
                                    durationMillis = 250,
                                    easing = FastOutLinearInEasing
                                )
                            )
                        ) {
                            MDSFloatingActionButton(
                                iconResource = R.drawable.ic_pencil,
                                containerColor = Mint03,
                                onClick = { /*TODO*/ }
                            )
                        }
                        if (expandableFab) Spacer(modifier = Modifier.height(10.dp))
                        val containerColor = if (expandableFab) Mint02 else Mint03
                        MDSFloatingActionButton(
                            modifier = Modifier.rotate(rotationAngle),
                            iconResource = R.drawable.ic_plus_default,
                            containerColor = containerColor,
                            onClick = {
                                expandableFab = !expandableFab
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerScreenPreview() {
    LedgerScreen()
}
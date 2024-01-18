package com.moneymong.moneymong.ledgerdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymong.moneymong.common.ui.DottedShape
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.component.selection.MDSSelection
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue03
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray01
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.ledgerdetail.view.LedgerDetailTopbarView
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun LedgerDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: LedgerDetailViewModel = hiltViewModel(),
    ledgerTransactionId: Int,
    popBackStack: () -> Unit
) {
    val state = viewModel.collectAsState().value
    val verticalScrollState = rememberScrollState()

    if (false) { // TODO
        MDSModal(
            icon = R.drawable.ic_warning_filled, // TODO
            title = "사진을 삭제하시겠습니까?", // TODO
            description = "삭제된 사진은 되돌릴 수 없습니다", // TODO
            negativeBtnText = "취소",
            positiveBtnText = "확인",
            onClickNegative = { /*TODO*/ },
            onClickPositive = { /*TODO*/ })
    }

    Scaffold(
        topBar = {
            LedgerDetailTopbarView(
                onClickPrev = popBackStack,
                onClickDelete = { /*TODO*/ }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .background(Gray01)
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MMHorizontalSpacing)
                    .clip(RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = Blue03, shape = RoundedCornerShape(16.dp))
                    .background(White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                        .padding(vertical = 28.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "수입·지출 출처",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "퍼스트 유통", // TODO
                        style = Body3,
                        color = Gray10
                    )
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "지출 금액",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "-1,600원", // TODO
                        style = Body3,
                        color = Gray10
                    )
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "날짜",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "2023년 11월 16일", // TODO
                        style = Body3,
                        color = Gray10
                    )
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "시간",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "15:36", // TODO
                        style = Body3,
                        color = Gray10
                    )
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "메모",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "내용없음", // TODO
                        style = Body3,
                        color = Gray10
                    )
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "영수증 (최대12장)",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyVerticalGrid(
                        modifier = modifier
                            .fillMaxSize()
                            .heightIn(max = 324.dp)
                            .background(White),
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(items = listOf(1, 2, 3)) { index, item -> // TODO
                            Box(
                                modifier = Modifier
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Blue01),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_plus_outline),
                                    contentDescription = null,
                                    tint = Blue04
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "증빙자료 (최대12장)",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyVerticalGrid(
                        modifier = modifier
                            .fillMaxSize()
                            .heightIn(max = 324.dp)
                            .background(White),
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        itemsIndexed(items = listOf(1, 2, 3)) { index, item -> // TODO
                            Box(
                                modifier = Modifier
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Blue01),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_plus_outline),
                                    contentDescription = null,
                                    tint = Blue04
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray03, shape = DottedShape(8.dp))
                    )
                    Text(
                        text = "작성자",
                        style = Body2,
                        color = Gray06
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "머니몽", // TODO
                        style = Body3,
                        color = Gray10
                    )
                }
            }
            MDSButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                text = "수정하기",
                size = MDSButtonSize.MEDIUM,
                type = MDSButtonType.PRIMARY,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerDetailScreenPreview() {
    LedgerDetailScreen(
        ledgerTransactionId = 0,
        popBackStack = {}
    )
}
package com.moneymong.moneymong.ocr_detail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moneymong.moneymong.common.ui.DottedShape
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.theme.Blue01
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray03
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.ocr_detail.view.OCRDetailTopbarView

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OCRDetailScreen(
    modifier: Modifier = Modifier,
    document: DocumentEntity?,
    navigateToHome: (NavOptions?) -> Unit,
    popBackStack: () -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    val verticalScrollState = rememberScrollState()

    val test = listOf(1, 2, 3, 4)
    Scaffold(
        topBar = {
            OCRDetailTopbarView(
                onClickPrev = popBackStack,
                onClickRegister = { /*TODO*/ }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .background(White)
                .padding(it)
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .height(240.dp),
                model = "https://dimg.donga.com/wps/NEWS/IMAGE/2023/05/12/119255016.1.jpg", // TODO
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "수입·지출 출처",
                        style = Body2,
                        color = Gray06
                    )
                    Text(
                        modifier = Modifier.noRippleClickable { /*TODO*/ },
                        text = "수정",
                        style = Body2,
                        color = Blue04
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text( // TODO textfiled로 변경 필요
                    modifier = Modifier.fillMaxWidth(),
                    text = "퍼스트 유통",
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "금액",
                        style = Body2,
                        color = Gray06
                    )
                    Text(
                        modifier = Modifier.noRippleClickable { /*TODO*/ },
                        text = "수정",
                        style = Body2,
                        color = Blue04
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text( // TODO textfiled로 변경 필요
                    modifier = Modifier.fillMaxWidth(),
                    text = "-1,600원",
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
                    modifier = Modifier.fillMaxWidth(),
                    text = "거래 유형",
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) { // TODO Selection 컴포넌트로 변경 예정
                    MDSButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = "지출",
                        type = MDSButtonType.PRIMARY,
                        size = MDSButtonSize.SMALL,
                        onClick = { /*TODO*/ },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MDSButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = "수입",
                        type = MDSButtonType.NEGATIVE,
                        size = MDSButtonSize.SMALL,
                        onClick = { /*TODO*/ },
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Gray03, shape = DottedShape(8.dp))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "날짜",
                        style = Body2,
                        color = Gray06
                    )
                    Text(
                        modifier = Modifier.noRippleClickable { /*TODO*/ },
                        text = "수정",
                        style = Body2,
                        color = Blue04
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text( // TODO textfiled로 변경 필요
                    modifier = Modifier.fillMaxWidth(),
                    text = "2023년 11월 16일",
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "메모",
                        style = Body2,
                        color = Gray06
                    )
                    Text(
                        modifier = Modifier.noRippleClickable { /*TODO*/ },
                        text = "수정",
                        style = Body2,
                        color = Blue04
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text( // TODO textfiled로 변경 필요
                    modifier = Modifier.fillMaxWidth(),
                    text = "내용없음",
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
                    modifier = Modifier.fillMaxWidth(),
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
                    state = lazyGridState,
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(items = test) { index, item ->
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
                    modifier = Modifier.fillMaxWidth(),
                    text = "작성자",
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "안병헌",
                    style = Body3,
                    color = Gray10
                )
                Spacer(modifier = Modifier.height(38.dp))
                MDSButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "등록하기",
                    onClick = { /*TODO*/ }
                )
                Spacer(modifier = Modifier.height(34.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OCRDetailScreenPreview() {
    OCRDetailScreen(document = null, navigateToHome = {}) {

    }
}
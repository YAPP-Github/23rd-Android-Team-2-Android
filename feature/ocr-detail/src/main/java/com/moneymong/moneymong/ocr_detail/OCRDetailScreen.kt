package com.moneymong.moneymong.ocr_detail

import android.util.Base64
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moneymong.moneymong.common.ui.DottedShape
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.selection.MDSSelection
import com.moneymong.moneymong.design_system.component.textfield.MDSNumberTextField
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSNumberTextFieldType
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
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
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OCRDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: OCRDetailViewModel = hiltViewModel(),
    document: DocumentEntity?,
    navigateToHome: (NavOptions?) -> Unit,
    popBackStack: () -> Unit
) {
    val state = viewModel.collectAsState().value
    val lazyGridState = rememberLazyGridState()
    val verticalScrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val test = listOf(1, 2, 3, 4)

    viewModel.collectSideEffect {
        when (it) {
            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        viewModel.init(document)
    }

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
                model = Base64.decode(state.receiptImage, Base64.DEFAULT),
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
                var isStoreNameFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isStoreNameFilled = !it.isFocused },
                    value = state.storeNameValue,
                    onValueChange = { viewModel.onChangeStoreNameValue(it) },
                    title = "수입·지출 출처",
                    placeholder = "",
                    isFilled = isStoreNameFilled,
                    isError = false,
                    helperText = "20자 이내로 입력해주세요",
                    maxCount = 20,
                    singleLine = true,
                    icon = MDSTextFieldIcons.Clear,
                    onIconClick = { viewModel.onChangeStoreNameValue(TextFieldValue()) },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(24.dp))
                var isTotalPriceFilled by remember { mutableStateOf(false) }
                MDSNumberTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isTotalPriceFilled = !it.isFocused },
                    value = state.totalPriceValue,
                    onValueChange = { viewModel.onChangeTotalPriceValue(it) },
                    type = MDSNumberTextFieldType.Expense,
                    title = "금액",
                    placeholder = "",
                    isFilled = isTotalPriceFilled,
                    singleLine = true,
                    onIconClick = { viewModel.onChangeTotalPriceValue(TextFieldValue()) },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "거래 유형",
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MDSSelection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .weight(1f),
                        text = "지출",
                        isSelected = true,
                        onClick = {}
                    )
                    MDSSelection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .weight(1f),
                        text = "수입",
                        isSelected = false,
                        onClick = {}
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                var isPaymentDateFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isPaymentDateFilled = !it.isFocused },
                    value = state.paymentDateValue,
                    onValueChange = { viewModel.onChangePaymentDateValue(it) },
                    title = "날짜",
                    placeholder = "",
                    isFilled = isPaymentDateFilled,
                    isError = false,
                    helperText = "올바른 날짜를 입력해주세요",
                    singleLine = true,
                    icon = MDSTextFieldIcons.Clear,
                    onIconClick = { viewModel.onChangePaymentDateValue(TextFieldValue()) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(24.dp))
                var isPaymentTimeFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isPaymentTimeFilled = !it.isFocused },
                    value = state.paymentTimeValue,
                    onValueChange = { viewModel.onChangePaymentTimeValue(it) },
                    title = "시간",
                    placeholder = "",
                    isFilled = isPaymentTimeFilled,
                    isError = false,
                    helperText = "올바른 날짜를 입력해주세요",
                    singleLine = true,
                    icon = MDSTextFieldIcons.Clear,
                    onIconClick = { viewModel.onChangePaymentTimeValue(TextFieldValue()) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(24.dp))
                var isMemoFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isMemoFilled = !it.isFocused },
                    value = state.memoValue,
                    onValueChange = { viewModel.onChangeMemoValue(it) },
                    title = "메모",
                    placeholder = "",
                    isFilled = isMemoFilled,
                    isError = false,
                    helperText = "300자 이내로 입력해주세요",
                    maxCount = 300,
                    singleLine = false,
                    icon = MDSTextFieldIcons.Clear,
                    onIconClick = { viewModel.onChangeMemoValue(TextFieldValue()) },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(24.dp))
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
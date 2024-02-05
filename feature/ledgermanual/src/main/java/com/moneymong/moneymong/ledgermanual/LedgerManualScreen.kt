package com.moneymong.moneymong.ledgermanual

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moneymong.moneymong.common.ext.base64ToFile
import com.moneymong.moneymong.common.ext.encodingBase64
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R.*
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.component.selection.MDSSelection
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.DateVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.PriceVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.TimeVisualTransformation
import com.moneymong.moneymong.design_system.theme.Blue03
import com.moneymong.moneymong.design_system.theme.Blue04
import com.moneymong.moneymong.design_system.theme.Body2
import com.moneymong.moneymong.design_system.theme.Body3
import com.moneymong.moneymong.design_system.theme.Gray06
import com.moneymong.moneymong.design_system.theme.Gray10
import com.moneymong.moneymong.design_system.theme.MMHorizontalSpacing
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.domain.param.ledger.FundType
import com.moneymong.moneymong.ledgermanual.view.LedgerManualTopbarView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LedgerManualScreen(
    modifier: Modifier = Modifier,
    viewModel: LedgerManualViewModel = hiltViewModel(),
    navigateToLedger: (ledgerPostSuccess: Boolean) -> Unit,
    popBackStack: () -> Unit
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value
    val verticalScrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                viewModel.postS3URLImage(it.encodingBase64(context).base64ToFile(context))
            }
        }
    )

    viewModel.collectSideEffect {
        when (it) {
            is LedgerManualSideEffect.LedgerManualOpenImagePicker -> {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }

            is LedgerManualSideEffect.LedgerManualNavigateToLedger -> {
                navigateToLedger(true)
            }

            is LedgerManualSideEffect.LedgerManualShowPopBackStackModal -> {
                viewModel.visiblePopBackStackModal(true)
            }

            is LedgerManualSideEffect.LegerManualHidePopBackStackModal -> {
                viewModel.visiblePopBackStackModal(false)
                if (it.navigate) {
                    navigateToLedger(false)
                }
            }

            is LedgerManualSideEffect.LedgerManualPostTransaction -> {
                viewModel.postLedgerTransaction()
            }
        }
    }

    BackHandler(onBack = { viewModel.eventEmit(LedgerManualSideEffect.LedgerManualShowPopBackStackModal) })

    if (state.showPopBackStackModal) {
        MDSModal(
            icon = drawable.ic_warning_filled,
            title = "정말 나가시겠습니까?",
            description = "작성한 내용이 저장되지 않습니다",
            negativeBtnText = "취소",
            positiveBtnText = "확인",
            onClickNegative = {
                viewModel.eventEmit(
                    LedgerManualSideEffect.LegerManualHidePopBackStackModal(
                        false
                    )
                )
            },
            onClickPositive = {
                viewModel.eventEmit(
                    LedgerManualSideEffect.LegerManualHidePopBackStackModal(
                        true
                    )
                )
            },
        )
    }

    Scaffold(
        topBar = {
            LedgerManualTopbarView(
                onClickClose = { viewModel.eventEmit(LedgerManualSideEffect.LedgerManualShowPopBackStackModal) }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .background(White)
                .pointerInput(key1 = Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MMHorizontalSpacing)
            ) {
                var isStoreNameFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isStoreNameFilled = !it.isFocused },
                    value = state.storeNameValue,
                    onValueChange = viewModel::onChangeStoreNameValue,
                    title = "수입·지출 출처",
                    placeholder = "점포명을 입력해주세요",
                    helperText = "20자 이하로 입력해주세요",
                    maxCount = 20,
                    isFilled = isStoreNameFilled,
                    isError = state.isStoreNameError,
                    singleLine = true,
                    icon = MDSTextFieldIcons.Clear,
                    onIconClick = { viewModel.onChangeStoreNameValue(TextFieldValue()) },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(24.dp))
                var isTotalPriceFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isTotalPriceFilled = !it.isFocused },
                    value = state.totalPriceValue,
                    onValueChange = viewModel::onChangeTotalPriceValue,
                    title = "금액",
                    placeholder = "거래 금액을 입력해주세요",
                    helperText = "999,999,999,999원 이하로 입력해주세요",
                    isFilled = isTotalPriceFilled,
                    isError = state.isTotalPriceError,
                    singleLine = true,
                    icon = MDSTextFieldIcons.Clear,
                    onIconClick = { viewModel.onChangeTotalPriceValue(TextFieldValue()) },
                    visualTransformation = PriceVisualTransformation(type = state.priceType),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "거래 유형",
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MDSSelection(
                        modifier = Modifier.weight(1f),
                        text = "지출",
                        isSelected = state.fundType == FundType.EXPENSE,
                        onClick = { viewModel.onChangeFundType(FundType.EXPENSE) }
                    )
                    MDSSelection(
                        modifier = Modifier.weight(1f),
                        text = "수입",
                        isSelected = state.fundType == FundType.INCOME,
                        onClick = { viewModel.onChangeFundType(FundType.INCOME) }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                var isPaymentDateFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isPaymentDateFilled = !it.isFocused },
                    value = state.paymentDateValue,
                    onValueChange = viewModel::onChangePaymentDateValue,
                    title = "날짜",
                    placeholder = "YYYY/MM/DD",
                    helperText = "올바른 날짜를 입력해주세요",
                    isFilled = isPaymentDateFilled,
                    isError = state.isPaymentDateError,
                    singleLine = true,
                    onIconClick = { viewModel.onChangePaymentDateValue(TextFieldValue()) },
                    icon = MDSTextFieldIcons.Clear,
                    visualTransformation = DateVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(24.dp))
                var isPaymentTimeFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isPaymentTimeFilled = !it.isFocused },
                    value = state.paymentTimeValue,
                    onValueChange = viewModel::onChangePaymentTimeValue,
                    title = "시간",
                    placeholder = "00:00:00 (24시 단위)",
                    helperText = "올바른 시간을 입력해주세요",
                    isFilled = isPaymentTimeFilled,
                    isError = state.isPaymentTimeError,
                    singleLine = true,
                    onIconClick = { viewModel.onChangePaymentTimeValue(TextFieldValue()) },
                    icon = MDSTextFieldIcons.Clear,
                    visualTransformation = TimeVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    text = buildAnnotatedString {
                        append("영수증 (최대 12장)\n")
                        withStyle(SpanStyle(color = Blue04)) {
                            append("*지출일 경우 영수증을 꼭 제출해주세요")
                        }
                    },
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxSize()
                        .heightIn(max = 504.dp)
                        .background(White),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (state.receiptList.size < 12) {
                        item {
                            Box(
                                modifier = Modifier
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Blue03,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(White)
                                    .noRippleClickable {
                                        viewModel.onChangeImageType(true)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = drawable.ic_plus_filled),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                    itemsIndexed(items = state.receiptList) { index, item ->
                        Box(
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            GlideImage(
                                modifier = Modifier.fillMaxSize(),
                                model = Uri.parse(item),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .noRippleClickable { viewModel.removeReceiptImage(item) }
                                    .padding(5.dp),
                                painter = painterResource(id = drawable.ic_close_filled),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "증빙 자료 (최대 12장)",
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxSize()
                        .heightIn(max = 504.dp)
                        .background(White),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (state.documentList.size < 12) {
                        item {
                            Box(
                                modifier = Modifier
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(
                                        width = 1.dp,
                                        color = Blue03,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(White)
                                    .noRippleClickable {
                                        viewModel.onChangeImageType(false)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = drawable.ic_plus_filled),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                    itemsIndexed(items = state.documentList) { index, item ->
                        Box(
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            GlideImage(
                                modifier = Modifier.fillMaxSize(),
                                model = Uri.parse(item),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .noRippleClickable { viewModel.removeDocumentImage(item) }
                                    .padding(5.dp),
                                painter = painterResource(id = drawable.ic_close_filled),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                var isMemoFilled by remember { mutableStateOf(false) }
                MDSTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { isMemoFilled = !it.isFocused },
                    value = state.memoValue,
                    onValueChange = viewModel::onChangeMemoValue,
                    title = "메모 (선택)",
                    placeholder = "메모할 내용을 입력하세요",
                    helperText = "300자 이하로 입력해주세요",
                    maxCount = 300,
                    singleLine = false,
                    isFilled = isMemoFilled,
                    isError = state.isMemoError,
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "작성자",
                    style = Body2,
                    color = Gray06
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.authorName,
                    style = Body3,
                    color = Gray10
                )
                Spacer(modifier = Modifier.height(64.dp))
                MDSButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "작성하기",
                    enabled = state.enabled,
                    type = MDSButtonType.PRIMARY,
                    size = MDSButtonSize.LARGE,
                    onClick = viewModel::onClickPostTransaction
                )
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerManualScreenPreview() {
    LedgerManualScreen(
        popBackStack = {},
        navigateToLedger = {}
    )
}
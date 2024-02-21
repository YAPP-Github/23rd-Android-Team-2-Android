package com.moneymong.moneymong.ledgerdetail

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.moneymong.moneymong.common.ui.DottedShape
import com.moneymong.moneymong.common.ui.noRippleClickable
import com.moneymong.moneymong.design_system.R
import com.moneymong.moneymong.design_system.component.button.MDSButton
import com.moneymong.moneymong.design_system.component.button.MDSButtonSize
import com.moneymong.moneymong.design_system.component.button.MDSButtonType
import com.moneymong.moneymong.design_system.component.modal.MDSModal
import com.moneymong.moneymong.design_system.component.textfield.MDSTextField
import com.moneymong.moneymong.design_system.component.textfield.util.MDSTextFieldIcons
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.DateVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.PriceVisualTransformation
import com.moneymong.moneymong.design_system.component.textfield.visualtransformation.TimeVisualTransformation
import com.moneymong.moneymong.design_system.error.ErrorDialog
import com.moneymong.moneymong.design_system.loading.LoadingScreen
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
import com.moneymong.moneymong.design_system.theme.Red03
import com.moneymong.moneymong.design_system.theme.White
import com.moneymong.moneymong.ledgerdetail.view.LedgerDetailTopbarView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LedgerDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: LedgerDetailViewModel = hiltViewModel(),
    ledgerTransactionId: Int,
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
            is LedgerDetailSideEffect.LedgerDetailEdit -> {
                verticalScrollState.scrollTo(0)
                viewModel.onChangeEditMode(true)
            }

            is LedgerDetailSideEffect.LedgerDetailEditDone -> {
                verticalScrollState.scrollTo(0)
                viewModel.onChangeEditMode(false)
                viewModel.ledgerTransactionEdit(detailId = ledgerTransactionId)
            }

            is LedgerDetailSideEffect.LedgerDetailFetchTransactionDetail -> {
                viewModel.fetchLedgerTransactionDetail(it.detailId)
            }

            is LedgerDetailSideEffect.LedgerDetailOpenImagePicker -> {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }

            is LedgerDetailSideEffect.LedgerDetailNavigateToLedger -> {
                popBackStack()
            }

            is LedgerDetailSideEffect.LedgerDetailConfirmModalNegative -> {
                viewModel.onChangeVisibleConfirmModal(false)
            }

            is LedgerDetailSideEffect.LedgerDetailConfirmModalPositive -> {
                viewModel.onChangeVisibleConfirmModal(false)
                viewModel.deleteLedgerDetail(detailId = ledgerTransactionId)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.eventEmit(
            LedgerDetailSideEffect.LedgerDetailFetchTransactionDetail(
                ledgerTransactionId
            )
        )
    }

    if (state.showErrorDialog) {
        ErrorDialog(message = state.errorMessage) {
            viewModel.onChangeErrorDialogVisible(false)
        }
    }

    if (state.showConfirmModal) {
        MDSModal(
            icon = R.drawable.ic_warning_filled,
            title = "장부 내역을 삭제하시겠습니까?",
            description = "삭제된 내역은 되돌릴 수 없습니다",
            negativeBtnText = "취소",
            positiveBtnText = "확인",
            onClickNegative = { viewModel.eventEmit(LedgerDetailSideEffect.LedgerDetailConfirmModalNegative) },
            onClickPositive = { viewModel.eventEmit(LedgerDetailSideEffect.LedgerDetailConfirmModalPositive) })
    }

    if (state.isLoading) {
        LoadingScreen(modifier = Modifier.fillMaxSize())
    } else {
        Scaffold(
            topBar = {
                LedgerDetailTopbarView(
                    title = "${state.fundTypeText} 상세내역",
                    useEditMode = state.useEditMode,
                    enabledDone = state.enabledEdit,
                    onClickPrev = popBackStack,
                    onClickDelete = { viewModel.onChangeVisibleConfirmModal(true) },
                    onClickDone = { viewModel.eventEmit(LedgerDetailSideEffect.LedgerDetailEditDone) }
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
                Spacer(modifier = Modifier.height(4.dp))
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
                        if (state.useEditMode) {
                            MDSTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.storeNameValue,
                                onValueChange = viewModel::onChangeStoreNameValue,
                                title = buildAnnotatedString {
                                    append("수입·지출 출처")
                                    withStyle(SpanStyle(color = Red03)) {
                                        append("*")
                                    }
                                },
                                placeholder = "",
                                isFilled = false,
                                isError = state.isStoreNameError,
                                helperText = "20자 이내로 입력해주세요",
                                maxCount = 20,
                                icon = MDSTextFieldIcons.Clear,
                                singleLine = true,
                                onIconClick = { viewModel.onChangeStoreNameValue(TextFieldValue()) },
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            )
                        } else {
                            Text(
                                text = "수입·지출 출처",
                                style = Body2,
                                color = Gray06
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.ledgerTransactionDetail?.storeInfo.orEmpty(),
                                style = Body3,
                                color = Gray10
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Gray03, shape = DottedShape(8.dp))
                        )
                        if (state.useEditMode) {
                            MDSTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.totalPriceValue,
                                onValueChange = viewModel::onChangeTotalPriceValue,
                                title = buildAnnotatedString {
                                    append("${state.fundTypeText} 금액")
                                    withStyle(SpanStyle(color = Red03)) {
                                        append("*")
                                    }
                                },
                                placeholder = "",
                                isFilled = false,
                                isError = state.isTotalPriceError,
                                helperText = "999,999,999원 이내로 입력해주세요",
                                onIconClick = { viewModel.onChangeTotalPriceValue(TextFieldValue()) },
                                singleLine = true,
                                icon = MDSTextFieldIcons.Clear,
                                visualTransformation = PriceVisualTransformation(type = state.priceType),
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        } else {
                            Text(
                                text = "${state.fundTypeText} 금액",
                                style = Body2,
                                color = Gray06
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${state.priceType.symbol}${state.totalPrice}원",
                                style = Body3,
                                color = Gray10
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Gray03, shape = DottedShape(8.dp))
                        )
                        if (state.useEditMode) {
                            MDSTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.paymentDateValue,
                                onValueChange = viewModel::onChangePaymentDateValue,
                                title = buildAnnotatedString {
                                    append("날짜")
                                    withStyle(SpanStyle(color = Red03)) {
                                        append("*")
                                    }
                                },
                                placeholder = "2024/01/01",
                                isFilled = false,
                                isError = state.isPaymentDateError,
                                helperText = "올바른 날짜를 입력해주세요",
                                onIconClick = { viewModel.onChangePaymentDateValue(TextFieldValue()) },
                                singleLine = true,
                                icon = MDSTextFieldIcons.Clear,
                                visualTransformation = DateVisualTransformation(),
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        } else {
                            Text(
                                text = "날짜",
                                style = Body2,
                                color = Gray06
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.formattedDate,
                                style = Body3,
                                color = Gray10
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Gray03, shape = DottedShape(8.dp))
                        )
                        if (state.useEditMode) {
                            MDSTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.paymentTimeValue,
                                onValueChange = viewModel::onChangePaymentTimeValue,
                                title = buildAnnotatedString {
                                    append("시간")
                                    withStyle(SpanStyle(color = Red03)) {
                                        append("*")
                                    }
                                },
                                placeholder = "00:00:00",
                                isFilled = false,
                                isError = state.isPaymentTimeError,
                                helperText = "올바른 시간을 입력해주세요",
                                onIconClick = { viewModel.onChangePaymentTimeValue(TextFieldValue()) },
                                singleLine = true,
                                icon = MDSTextFieldIcons.Clear,
                                visualTransformation = TimeVisualTransformation(),
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        } else {
                            Text(
                                text = "시간",
                                style = Body2,
                                color = Gray06
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.formattedTime,
                                style = Body3,
                                color = Gray10
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Gray03, shape = DottedShape(8.dp))
                        )
                        if (state.useEditMode) {
                            MDSTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.memoValue,
                                onValueChange = { viewModel.onChangeMemoValue(it) },
                                title = "메모",
                                placeholder = "",
                                isFilled = false,
                                isError = state.isMemoError,
                                helperText = "300자 이내로 입력해주세요",
                                maxCount = 300,
                                singleLine = false,
                                icon = MDSTextFieldIcons.Clear,
                                onIconClick = { viewModel.onChangeMemoValue(TextFieldValue()) },
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                            )
                        } else {
                            Text(
                                text = "메모",
                                style = Body2,
                                color = Gray06
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.ledgerTransactionDetail?.description.orEmpty(),
                                style = Body3,
                                color = Gray10
                            )
                        }
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
                                .heightIn(max = 504.dp)
                                .background(White),
                            columns = GridCells.Fixed(3),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            val showAddReceipt = state.useEditMode && state.receiptList.size < 12
                            if (showAddReceipt) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(120.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Blue01)
                                            .noRippleClickable {
                                                viewModel.onChangeImageType(isReceipt = true)
                                            },
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
                            itemsIndexed(items = state.receiptList) { index, item ->
                                Box(
                                    modifier = Modifier
                                        .height(120.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                ) {
                                    GlideImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = item,
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth
                                    )
                                    if (state.useEditMode) {
                                        Icon(
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .noRippleClickable {
                                                    viewModel.onClickRemoveReceipt(item)
                                                }
                                                .padding(5.dp),
                                            painter = painterResource(id = R.drawable.ic_close_filled),
                                            contentDescription = null,
                                            tint = Color.Unspecified
                                        )
                                    }
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
                                .heightIn(max = 504.dp)
                                .background(White),
                            columns = GridCells.Fixed(3),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            val showAddDocument = state.useEditMode && state.documentList.size < 12
                            if (showAddDocument) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .height(120.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Blue01)
                                            .noRippleClickable {
                                                viewModel.onChangeImageType(isReceipt = false)
                                            },
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
                            itemsIndexed(items = state.documentList) { index, item ->
                                Box(
                                    modifier = Modifier
                                        .height(120.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                ) {
                                    GlideImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = item,
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth
                                    )
                                    if (state.useEditMode) {
                                        Icon(
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .noRippleClickable {
                                                    viewModel.onClickRemoveDocument(item)
                                                }
                                                .padding(5.dp),
                                            painter = painterResource(id = R.drawable.ic_close_filled),
                                            contentDescription = null,
                                            tint = Color.Unspecified
                                        )
                                    }
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
                            text = state.ledgerTransactionDetail?.authorName.orEmpty(),
                            style = Body3,
                            color = Gray10
                        )
                    }
                }
                if (state.isStaff) {
                    if (state.useEditMode) {
                        MDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 20.dp),
                            text = "완료하기",
                            enabled = state.enabledEdit,
                            size = MDSButtonSize.LARGE,
                            type = MDSButtonType.PRIMARY,
                            onClick = { viewModel.eventEmit(LedgerDetailSideEffect.LedgerDetailEditDone) }
                        )
                    } else {
                        MDSButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 20.dp),
                            text = "수정하기",
                            size = MDSButtonSize.LARGE,
                            type = MDSButtonType.PRIMARY,
                            onClick = { viewModel.eventEmit(LedgerDetailSideEffect.LedgerDetailEdit) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LedgerDetailScreenPreview() {
    LedgerDetailScreen(
        ledgerTransactionId = 0,
        navigateToLedger = {},
        popBackStack = {}
    )
}
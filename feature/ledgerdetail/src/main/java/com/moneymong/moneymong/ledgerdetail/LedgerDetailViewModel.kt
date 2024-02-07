package com.moneymong.moneymong.ledgerdetail

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.error.MoneyMongError
import com.moneymong.moneymong.common.ext.toDateFormat
import com.moneymong.moneymong.common.ui.isValidPaymentDate
import com.moneymong.moneymong.common.ui.isValidPaymentTime
import com.moneymong.moneymong.common.ui.validateValue
import com.moneymong.moneymong.domain.entity.ledgerdetail.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.usecase.ledgerdetail.FetchLedgerTransactionDetailUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.DeleteLedgerDetailUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.DeleteLedgerDocumentTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.DeleteLedgerReceiptTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.PostLedgerDocumentTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.PostLedgerReceiptTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.UpdateLedgerTransactionDetailUseCase
import com.moneymong.moneymong.domain.usecase.ocr.PostFileUploadUseCase
import com.moneymong.moneymong.ledgerdetail.navigation.LedgerDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.inject.Inject

@OptIn(OrbitExperimental::class)
@HiltViewModel
class LedgerDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchLedgerTransactionDetailUseCase: FetchLedgerTransactionDetailUseCase,
    private val updateLedgerTransactionDetailUseCase: UpdateLedgerTransactionDetailUseCase,
    private val postLedgerReceiptTransactionUseCase: PostLedgerReceiptTransactionUseCase,
    private val postLedgerDocumentTransactionUseCase: PostLedgerDocumentTransactionUseCase,
    private val deleteLedgerReceiptTransactionUseCase: DeleteLedgerReceiptTransactionUseCase,
    private val deleteLedgerDocumentTransactionUseCase: DeleteLedgerDocumentTransactionUseCase,
    private val postFileUploadUseCase: PostFileUploadUseCase,
    private val deleteLedgerDetailUseCase: DeleteLedgerDetailUseCase
) : BaseViewModel<LedgerDetailState, LedgerDetailSideEffect>(LedgerDetailState()) {

    init {
        onChangeStaffStatus(isStaff = LedgerDetailArgs(savedStateHandle).isStaff)
    }

    fun ledgerTransactionEdit(detailId: Int) = intent {
        postLedgerReceiptTransaction(detailId)
        deleteLedgerReceiptTransaction(detailId)
        postLedgerDocumentTransaction(detailId)
        deleteLedgerDocumentTransaction(detailId)
        updateLedgerTransactionDetail(detailId)
    }

    fun fetchLedgerTransactionDetail(detailId: Int) = intent {
        reduce { state.copy(isLoading = true) }
        fetchLedgerTransactionDetailUseCase(detailId)
            .onSuccess {
                reduce { state.copy(ledgerTransactionDetail = it) }
                initTextValue(it)
            }.onFailure {
                showErrorDialog(it.message)
            }.also { reduce { state.copy(isLoading = false) } }
    }

    fun updateLedgerTransactionDetail(detailId: Int) = intent {
        reduce { state.copy(isLoading = true) }
        delay(1000) // 사진 수정 내용이 DB 반영되기 까지 걸리는 시간을 대응하기 위해 delay 설정
        val param = LedgerTransactionDetailParam(
            detailId = detailId,
            storeInfo = state.storeNameValue.text,
            amount = state.totalPriceValue.text.toInt(),
            description = state.memoValue.text,
            paymentDate = state.formattedPaymentDate
        )
        updateLedgerTransactionDetailUseCase(param)
            .onSuccess {
                reduce { state.copy(ledgerTransactionDetail = it) }
                initTextValue(it)
            }.onFailure {
                showErrorDialog(it.message)
            }.also { reduce { state.copy(isLoading = false) } }
    }

    fun postLedgerReceiptTransaction(detailId: Int) = intent {
        if (state.receiptList.isNotEmpty()) {
            reduce { state.copy(isLoading = true) }
            val mapToOriginalUrl =
                state.ledgerTransactionDetail?.receiptImageUrls?.map { it.receiptImageUrl }
                    .orEmpty()
            val param = LedgerReceiptParam(
                detailId = detailId,
                receiptImageUrls = state.receiptList - mapToOriginalUrl.toSet()
            )
            postLedgerReceiptTransactionUseCase(param)
                .onFailure {
                    showErrorDialog(it.message)
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun postLedgerDocumentTransaction(detailId: Int) = intent {
        if (state.documentList.isNotEmpty()) {
            reduce { state.copy(isLoading = true) }
            val mapToOriginalUrl =
                state.ledgerTransactionDetail?.documentImageUrls?.map { it.documentImageUrl }
                    .orEmpty()
            val param = LedgerDocumentParam(
                detailId = detailId,
                documentImageUrls = state.documentList - mapToOriginalUrl.toSet()
            )
            postLedgerDocumentTransactionUseCase(param)
                .onFailure {
                    showErrorDialog(it.message)
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun deleteLedgerReceiptTransaction(detailId: Int) = intent {
        if (state.receiptIdList.isNotEmpty()) {
            reduce { state.copy(isLoading = true) }
            state.receiptIdList.forEach { receiptId ->
                val param = DeleteLedgerReceiptParam(detailId = detailId, receiptId = receiptId)
                deleteLedgerReceiptTransactionUseCase(param)
                    .onFailure {
                        showErrorDialog(it.message)
                    }.also { reduce { state.copy(isLoading = false) } }
            }
        }
    }

    fun deleteLedgerDocumentTransaction(detailId: Int) = intent {
        if (state.documentIdList.isNotEmpty()) {
            reduce { state.copy(isLoading = true) }
            state.documentIdList.forEach { documentId ->
                val param = DeleteLedgerDocumentParam(detailId = detailId, documentId = documentId)
                deleteLedgerDocumentTransactionUseCase(param)
                    .onFailure {
                        showErrorDialog(it.message)
                    }.also { reduce { state.copy(isLoading = false) } }
            }
        }
    }

    fun deleteLedgerDetail(detailId: Int) = intent {
        reduce { state.copy(isLoading = true) }
        deleteLedgerDetailUseCase(detailId)
            .onSuccess {
                postSideEffect(LedgerDetailSideEffect.LedgerDetailNavigateToLedger)
            }.onFailure {
                showErrorDialog(it.message)
            }.also { reduce { state.copy(isLoading = false) } }
    }

    fun postS3URLImage(imageFile: File?) = intent {
        imageFile?.let {
            if (!state.isLoading) {
                reduce { state.copy(isLoading = true) }
                val file = FileUploadParam(it, "ledgerDetail")
                postFileUploadUseCase(file)
                    .onSuccess { response ->
                        state.isReceipt?.let { isReceipt ->
                            if (isReceipt) {
                                reduce { state.copy(receiptList = state.receiptList + response.path) }
                            } else {
                                reduce { state.copy(documentList = state.documentList + response.path) }
                            }
                        }
                    }.onFailure {
                        showErrorDialog(it.message)
                    }.also {
                        reduce {
                            state.copy(
                                isLoading = false,
                                isReceipt = null
                            )
                        }
                    }
            }
        }
    }

    fun onClickRemoveReceipt(receiptImage: String) = intent {
        state.ledgerTransactionDetail?.let {
            val receiptId = it.receiptImageUrls.find { it.receiptImageUrl == receiptImage }?.id ?: 0
            val isOriginalReceipt =
                it.receiptImageUrls.map { it.receiptImageUrl }.contains(receiptImage)
            if (isOriginalReceipt) {
                reduce {
                    state.copy(
                        receiptList = state.receiptList - receiptImage,
                        receiptIdList = state.receiptIdList + receiptId
                    )
                }
            } else {
                reduce {
                    state.copy(
                        receiptList = state.receiptList - receiptImage
                    )
                }
            }
        }
    }

    fun onClickRemoveDocument(documentImage: String) = intent {
        state.ledgerTransactionDetail?.let {
            val documentId =
                it.documentImageUrls.find { it.documentImageUrl == documentImage }?.id ?: 0
            val isOriginalDocument =
                it.documentImageUrls.map { it.documentImageUrl }.contains(documentImage)
            if (isOriginalDocument) {
                reduce {
                    state.copy(
                        documentList = state.documentList - documentImage,
                        documentIdList = state.documentIdList + documentId
                    )
                }
            } else {
                reduce {
                    state.copy(
                        documentList = state.documentList - documentImage
                    )
                }
            }
        }
    }

    private fun initTextValue(ledgerTransactionDetail: LedgerTransactionDetailEntity) = intent {
        reduce {
            state.copy(
                storeNameValue = state.storeNameValue.copy(text = ledgerTransactionDetail.storeInfo),
                totalPriceValue = state.totalPriceValue.copy(text = ledgerTransactionDetail.amount.toString()),
                paymentDateValue = state.paymentDateValue.copy(
                    text = ledgerTransactionDetail.paymentDate.toDateFormat(
                        "yyyyMMdd"
                    )
                ),
                paymentTimeValue = state.paymentDateValue.copy(
                    text = ledgerTransactionDetail.paymentDate.toDateFormat(
                        "HHmmss"
                    )
                ),
                memoValue = state.memoValue.copy(text = ledgerTransactionDetail.description),
                receiptList = ledgerTransactionDetail.receiptImageUrls.map { it.receiptImageUrl },
                documentList = ledgerTransactionDetail.documentImageUrls.map { it.documentImageUrl },
                receiptIdList = emptyList(),
                documentIdList = emptyList()
            )
        }
    }

    fun onChangeStoreNameValue(value: TextFieldValue) = blockingIntent {
        val validate = value.text.validateValue(length = 20)
        if (!validate) {
            reduce { state.copy(isStoreNameError = true) }
        } else {
            reduce { state.copy(isStoreNameError = false) }
        }
        reduce { state.copy(storeNameValue = value) }
    }

    fun onChangeTotalPriceValue(value: TextFieldValue) = blockingIntent {
        val validate = value.text.validateValue(length = 12, isDigit = true)
        if (validate) {
            val elvisValue = value.text.ifEmpty { "0" }
            if (elvisValue.toLong() > MAX_TOTAL_PRICE) {
                reduce { state.copy(isTotalPriceError = true) }
            } else {
                reduce { state.copy(isTotalPriceError = false) }
            }

            reduce { state.copy(totalPriceValue = value) }
        }
    }

    fun onChangePaymentDateValue(value: TextFieldValue) = blockingIntent {
        val validate = value.text.validateValue(length = 8, isDigit = true)
        if (validate) {
            val isPaymentDateError = !value.text.isValidPaymentDate()
            reduce {
                state.copy(
                    paymentDateValue = value,
                    isPaymentDateError = isPaymentDateError
                )
            }
        }
    }

    fun onChangePaymentTimeValue(value: TextFieldValue) = blockingIntent {
        val validate = value.text.validateValue(length = 6, isDigit = true)
        if (validate) {
            val isPaymentTimeError = !value.text.isValidPaymentTime()
            reduce {
                state.copy(
                    paymentTimeValue = value,
                    isPaymentTimeError = isPaymentTimeError
                )
            }
        }
    }

    fun onChangeMemoValue(value: TextFieldValue) = blockingIntent {
        val validate = value.text.validateValue(length = 300)
        reduce {
            state.copy(
                memoValue = value,
                isMemoError = !validate
            )
        }
    }

    fun onChangeEditMode(useEditMode: Boolean) = intent {
        reduce { state.copy(useEditMode = useEditMode) }
    }

    fun onChangeImageType(isReceipt: Boolean) = intent {
        reduce { state.copy(isReceipt = isReceipt) }
        postSideEffect(LedgerDetailSideEffect.LedgerDetailOpenImagePicker)
    }

    fun onChangeVisibleConfirmModal(visible: Boolean) = intent {
        reduce { state.copy(showConfirmModal = visible) }
    }

    fun onChangeErrorDialogVisible(visible: Boolean) = intent {
        reduce { state.copy(showErrorDialog = visible) }
    }

    private fun onChangeStaffStatus(isStaff: Boolean) = intent {
        reduce { state.copy(isStaff = isStaff) }
    }

    private fun showErrorDialog(message: String?) = intent {
        reduce {
            state.copy(
                showErrorDialog = true,
                errorMessage = message ?: MoneyMongError.UnExpectedError.message
            )
        }
    }

    companion object {
        const val MAX_TOTAL_PRICE = 999999999
    }
}
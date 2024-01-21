package com.moneymong.moneymong.ledgerdetail

import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ext.toDateFormat
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.usecase.ledger.FetchLedgerTransactionDetailUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.DeleteLedgerDocumentTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.DeleteLedgerReceiptTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.PostLedgerDocumentTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.PostLedgerReceiptTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ledgerdetail.UpdateLedgerTransactionDetailUseCase
import com.moneymong.moneymong.domain.usecase.ocr.PostFileUploadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LedgerDetailViewModel @Inject constructor(
    private val fetchLedgerTransactionDetailUseCase: FetchLedgerTransactionDetailUseCase,
    private val updateLedgerTransactionDetailUseCase: UpdateLedgerTransactionDetailUseCase,
    private val postLedgerReceiptTransactionUseCase: PostLedgerReceiptTransactionUseCase,
    private val postLedgerDocumentTransactionUseCase: PostLedgerDocumentTransactionUseCase,
    private val deleteLedgerReceiptTransactionUseCase: DeleteLedgerReceiptTransactionUseCase,
    private val deleteLedgerDocumentTransactionUseCase: DeleteLedgerDocumentTransactionUseCase,
    private val postFileUploadUseCase: PostFileUploadUseCase
) : BaseViewModel<LedgerDetailState, LedgerDetailSideEffect>(LedgerDetailState()) {

    fun fetchLedgerTransactionDetail(detailId: Int) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            fetchLedgerTransactionDetailUseCase(detailId)
                .onSuccess {
                    reduce { state.copy(ledgerTransactionDetail = it) }
                    initTextValue(it)
                }.onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun updateLedgerTransactionDetail(detailId: Int) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
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
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun postLedgerReceiptTransaction(detailId: Int) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val param =
                LedgerReceiptParam(detailId = detailId, receiptImageUrls = state.receiptList)
            postLedgerReceiptTransactionUseCase(param)
                .onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun postLedgerDocumentTransaction(detailId: Int) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val param =
                LedgerDocumentParam(detailId = detailId, documentImageUrls = state.documentList)
            postLedgerDocumentTransactionUseCase(param)
                .onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun deleteLedgerReceiptTransaction(detailId: Int, receiptId: Int) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val param = DeleteLedgerReceiptParam(detailId = detailId, receiptId = receiptId)
            deleteLedgerReceiptTransactionUseCase(param)
                .onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun deleteLedgerDocumentTransaction(detailId: Int, documentId: Int) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val param = DeleteLedgerDocumentParam(detailId = detailId, documentId = documentId)
            deleteLedgerDocumentTransactionUseCase(param)
                .onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
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
                        // TODO
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

    fun onClickRemoveReceipt(index: Int) = intent {
        state.ledgerTransactionDetail?.let {
            val receiptImage = state.receiptList[index]
            val isOriginalReceipt = it.receiptImageUrls.map { it.receiptImageUrl }.contains(state.receiptList[index])
            if (isOriginalReceipt) {
                reduce {
                    state.copy(
                        receiptList = state.receiptList - receiptImage,
                        receiptIdList = state.receiptIdList + it.receiptImageUrls[index].id
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

    fun onClickRemoveDocument(index: Int) = intent {
        state.ledgerTransactionDetail?.let {
            val documentImage = state.documentList[index]
            val isOriginalDocument = it.documentImageUrls.map { it.documentImageUrl }.contains(documentImage)
            if (isOriginalDocument) {
                reduce {
                    state.copy(
                        documentList = state.documentList - documentImage,
                        documentIdList = state.documentIdList + it.documentImageUrls[index].id
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
                documentList = ledgerTransactionDetail.documentImageUrls.map { it.documentImageUrl }
            )
        }
    }

    fun onChangeStoreNameValue(value: TextFieldValue) = blockingIntent {
        val validate = validateValue(targetValue = value, length = 20)
        if (!validate) {
            reduce { state.copy(isStoreNameError = true) }
        } else {
            reduce { state.copy(isStoreNameError = false) }
        }
        reduce { state.copy(storeNameValue = value) }
    }

    fun onChangeTotalPriceValue(value: TextFieldValue) = blockingIntent {
        val validate = validateValue(targetValue = value, length = 12, isDigit = true)
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
        val validate = validateValue(targetValue = value, length = 8, isDigit = true)
        if (validate) {
            reduce { state.copy(paymentDateValue = value) }
        }
    }

    fun onChangePaymentTimeValue(value: TextFieldValue) = blockingIntent {
        val validate = validateValue(targetValue = value, length = 6, isDigit = true)
        if (validate) {
            reduce { state.copy(paymentTimeValue = value) }
        }
    }

    fun onChangeMemoValue(value: TextFieldValue) = blockingIntent {
        val validate = validateValue(targetValue = value, length = 300)
        if (!validate) {
            reduce { state.copy(isMemoError = true) }
        } else {
            reduce { state.copy(isMemoError = false) }
        }
        reduce { state.copy(memoValue = value) }
    }

    fun onChangeEditMode(useEditMode: Boolean) = intent {
        reduce { state.copy(useEditMode = useEditMode) }
    }

    fun onChangeImageType(isReceipt: Boolean) = intent {
        reduce { state.copy(isReceipt = isReceipt) }
        postSideEffect(LedgerDetailSideEffect.LedgerDetailOpenImagePicker)
    }

    private fun validateValue(
        targetValue: TextFieldValue,
        length: Int,
        isDigit: Boolean = false
    ) = if (isDigit) {
        targetValue.text.isDigitsOnly() && targetValue.text.length <= length
    } else {
        targetValue.text.length <= length
    }

    companion object {
        const val MAX_TOTAL_PRICE = 999999999
    }
}
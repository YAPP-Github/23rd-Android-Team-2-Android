package com.moneymong.moneymong.ocr_detail

import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.net.toFile
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ext.base64ToFile
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.param.ledger.FundType
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.usecase.ledger.PostLedgerTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ocr.PostFileUploadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OCRDetailViewModel @Inject constructor(
    private val prefs: SharedPreferences,
    private val postLedgerTransactionUseCase: PostLedgerTransactionUseCase,
    private val postFileUploadUseCase: PostFileUploadUseCase
) : BaseViewModel<OCRDetailState, OCRDetailSideEffect>(OCRDetailState()) {

    init {
        fetchReceiptImage()
    }

    fun init(document: DocumentEntity?) = intent {
        val receipt = document?.images?.first()?.receipt?.result
        val paymentDateString = receipt?.paymentInfo?.date?.formatted.run {
            "${this?.year ?: ""}${this?.month ?: ""}${this?.day ?: ""}"
        }
        val paymentTimeString = receipt?.paymentInfo?.time?.formatted.run {
            "${this?.hour ?: ""}${this?.minute ?: ""}${this?.second ?: ""}"
        }
        reduce {
            state.copy(
                document = document,
                storeNameValue = state.storeNameValue.copy(text = receipt?.storeInfo?.name?.text.orEmpty()),
                totalPriceValue = state.totalPriceValue.copy(text = receipt?.totalPrice?.price?.formatted?.value.orEmpty()),
                paymentDateValue = state.paymentDateValue.copy(text = paymentDateString),
                paymentTimeValue = state.paymentTimeValue.copy(text = paymentTimeString)
            )
        }
    }

    fun postLedgerTransaction() = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val ledgerTransactionParam = LedgerTransactionParam(
                id = 1,
                storeInfo = state.storeNameValue.text,
                fundType = FundType.EXPENSE,
                amount = state.totalPriceValue.text.toInt(),
                description = state.memoValue.text,
                paymentDate = state.postPaymentDate,
                receiptImageUrls = state.receiptImageUrls,
                documentImageUrls = state.documentImageUrls
            )
            postLedgerTransactionUseCase(ledgerTransactionParam)
                .onSuccess {
                    postSideEffect(OCRDetailSideEffect.OCRDetailNavigateToHome)
                }.onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun postDocumentImage(imageFile: File?, isReceipt: Boolean = false) = intent {
        imageFile?.let {
            if (!state.isLoading) {
                reduce { state.copy(isLoading = true) }
                val file = FileUploadParam(it, "ocr")
                postFileUploadUseCase(file)
                    .onSuccess {
                        if (isReceipt) {
                            reduce { state.copy(receiptImageUrls = listOf(it.path)) }
                        } else {
                            reduce { state.copy(documentImageUrls = state.documentImageUrls + it.path) }
                        }
                    }.onFailure {
                        // TODO
                    }.also { reduce { state.copy(isLoading = false) } }
            }
        }
    }

    fun onClickPostLedger() = intent {
        postDocumentImage(imageFile = state.receiptFile, isReceipt = true)
        postLedgerTransaction()
    }

    fun addDocumentImage(file: File?) = intent {
        val newDocumentUris = state.documentImageUrls.toMutableList()
        if (newDocumentUris.size == 12) {
            newDocumentUris.removeFirst()
            reduce { state.copy(documentImageUrls = newDocumentUris) }
        }
        postDocumentImage(file)
    }

    fun removeDocumentImage(uriString: String) = intent {
        val newDocumentUris = state.documentImageUrls.toMutableList()
        if (state.documentImageUrls.size == 12 && state.documentImageUrls.first().isNotEmpty()) {
            newDocumentUris.add(0, "")
        }
        reduce { state.copy(documentImageUrls = newDocumentUris - uriString) }
    }

    fun reduceReceiptFile(file: File?) = intent {
        reduce { state.copy(receiptFile = file) }
    }

    private fun fetchReceiptImage() = blockingIntent {
        val receiptImage = prefs.getString("receiptImage", "").orEmpty()
        reduce { state.copy(receiptImage = receiptImage) }
    }

    fun onChangeStoreNameValue(value: TextFieldValue) = blockingIntent {
        if (value.text.length <= 20) {
            reduce {
                state.copy(storeNameValue = value)
            }
        }
    }

    fun onChangeTotalPriceValue(value: TextFieldValue) = blockingIntent {
        if (value.text.length <= 9) {
            reduce {
                state.copy(totalPriceValue = value)
            }
        }
    }

    fun onChangePaymentDateValue(value: TextFieldValue) = blockingIntent {
        if (value.text.length <= 8) {
            reduce {
                state.copy(paymentDateValue = value)
            }
        }
    }

    fun onChangePaymentTimeValue(value: TextFieldValue) = blockingIntent {
        if (value.text.length <= 6) {
            reduce {
                state.copy(paymentTimeValue = value)
            }
        }
    }

    fun onChangeMemoValue(value: TextFieldValue) = blockingIntent {
        if (value.text.length <= 300) {
            reduce {
                state.copy(memoValue = value)
            }
        }
    }
}
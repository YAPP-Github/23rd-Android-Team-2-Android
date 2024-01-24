package com.moneymong.moneymong.ledgermanual

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ui.isValidPaymentDate
import com.moneymong.moneymong.common.ui.isValidPaymentTime
import com.moneymong.moneymong.common.ui.validateValue
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.usecase.ocr.PostFileUploadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LedgerManualViewModel @Inject constructor(
    private val postFileUploadUseCase: PostFileUploadUseCase
) : BaseViewModel<LedgerManualState, LedgerManualSideEffect>(LedgerManualState()) {

    fun postS3URLImage(imageFile: File?) = intent {
        imageFile?.let {
            if (!state.isLoading) {
                reduce { state.copy(isLoading = true) }
                val file = FileUploadParam(it, "ledgerManual")
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

    fun onChangeImageType(isReceipt: Boolean) = intent {
        reduce { state.copy(isReceipt = isReceipt) }
        postSideEffect(LedgerManualSideEffect.LedgerManualOpenImagePicker)
    }

    fun removeReceiptImage(image: String) = intent {
        reduce { state.copy(receiptList = state.receiptList - image) }
    }

    fun removeDocumentImage(image: String) = intent {
        reduce { state.copy(documentList = state.documentList - image) }
    }

    companion object {
        const val MAX_TOTAL_PRICE = 999999999
    }
}
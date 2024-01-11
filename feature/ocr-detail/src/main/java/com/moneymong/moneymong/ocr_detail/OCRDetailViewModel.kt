package com.moneymong.moneymong.ocr_detail

import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OCRDetailViewModel @Inject constructor(
    private val prefs: SharedPreferences
) : BaseViewModel<OCRDetailState, OCRDetailSideEffect>(OCRDetailState()) {

    init {
        fetchReceiptImage()
    }

    fun init(document: DocumentEntity?) = intent {
        val receipt = document?.images?.first()?.receipt?.result
        val paymentDateString = receipt?.paymentInfo?.date?.formatted.run {
            var str = ""
            this?.year?.let { str += "${it}년 " }
            this?.month?.let { str += "${it}월 " }
            this?.day?.let { str += "${it}일" }
            str
        }
        val paymentTimeString = receipt?.paymentInfo?.time?.formatted.run {
            "${this?.hour ?: "00"}:${this?.minute ?: "00"}:${this?.second ?: "00"}"
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

    fun addDocumentImage(uri: Uri) = intent {
        val newDocumentUris = state.documentImageUris.toMutableList()
        if (state.documentImageUris.size == 12) {
            newDocumentUris.removeFirst()
        }
        reduce { state.copy(documentImageUris = newDocumentUris + uri.toString()) }
    }

    fun removeDocumentImage(uriString: String) = intent {
        val newDocumentUris = state.documentImageUris.toMutableList()
        if (state.documentImageUris.size == 12 && state.documentImageUris.first().isNotEmpty()) {
            newDocumentUris.add(0, "")
        }
        reduce { state.copy(documentImageUris = newDocumentUris - uriString) }
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
        reduce {
            state.copy(totalPriceValue = value)
        }
    }

    fun onChangePaymentDateValue(value: TextFieldValue) = blockingIntent {
        reduce {
            state.copy(paymentDateValue = value)
        }
    }

    fun onChangePaymentTimeValue(value: TextFieldValue) = blockingIntent {
        reduce {
            state.copy(paymentTimeValue = value)
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
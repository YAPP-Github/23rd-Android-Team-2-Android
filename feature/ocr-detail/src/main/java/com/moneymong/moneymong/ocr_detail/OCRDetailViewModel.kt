package com.moneymong.moneymong.ocr_detail

import android.content.SharedPreferences
import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ui.isValidPaymentDate
import com.moneymong.moneymong.common.ui.isValidPaymentTime
import com.moneymong.moneymong.common.ui.validateValue
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.param.ledger.FundType
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.usecase.agency.FetchAgencyIdUseCase
import com.moneymong.moneymong.domain.usecase.ledger.PostLedgerTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ocr.PostFileUploadUseCase
import com.moneymong.moneymong.domain.usecase.user.FetchUserNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
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
    private val postFileUploadUseCase: PostFileUploadUseCase,
    private val fetchAgencyIdUseCase: FetchAgencyIdUseCase,
    private val fetchUserNicknameUseCase: FetchUserNicknameUseCase
) : BaseViewModel<OCRDetailState, OCRDetailSideEffect>(OCRDetailState()) {

    init {
        fetchUserInfo()
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

    @OptIn(OrbitExperimental::class)
    fun fetchUserInfo() = blockingIntent {
        val agencyId = fetchAgencyIdUseCase(Unit)
        val userNickname = fetchUserNicknameUseCase(Unit)
        reduce {
            state.copy(
                agencyId = agencyId,
                authorName = userNickname
            )
        }
    }

    fun postLedgerTransaction() = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            // empty string을 제거하고 요청을 보내기 위함
            val documentImageUrls = state.documentImageUrls - ""
            val ledgerTransactionParam = LedgerTransactionParam(
                id = state.agencyId,
                storeInfo = state.storeNameValue.text,
                fundType = state.fundType,
                amount = state.totalPriceValue.text.toInt(),
                description = state.memoValue.text,
                paymentDate = state.postPaymentDate,
                receiptImageUrls = state.receiptImageUrls,
                documentImageUrls = documentImageUrls.ifEmpty {
                    emptyList()
                }
            )
            postLedgerTransactionUseCase(ledgerTransactionParam)
                .onSuccess {
                    postSideEffect(OCRDetailSideEffect.OCRDetailNavigateToLedger)
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
                            postLedgerTransaction()
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
        val validate = value.text.validateValue(length = 20)
        if (!validate) {
            reduce { state.copy(isStoreNameError = true) }
        } else {
            reduce { state.copy(isStoreNameError = false) }
        }
        reduce { state.copy(storeNameValue = value) }
    }

    fun onChangeTotalPriceValue(value: TextFieldValue) = blockingIntent {
        val trimValue = trimStartWithZero(value)
        val validate = trimValue.text.validateValue(length = 12, isDigit = true)
        if (validate) {
            val elvisValue = value.text.ifEmpty { "0" }
            if (elvisValue.toLong() > MAX_TOTAL_PRICE) {
                reduce { state.copy(isTotalPriceError = true) }
            } else {
                reduce { state.copy(isTotalPriceError = false) }
            }

            reduce { state.copy(totalPriceValue = trimValue) }
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

    fun onChangeFundType(fundType: FundType) = intent {
        reduce { state.copy(fundType = fundType) }
    }

    private fun trimStartWithZero(value: TextFieldValue) =
        if (value.text.isNotEmpty() && value.text.all { it == '0' }) {
            value.copy(text = "0")
        } else {
            value.copy(text = value.text.trimStart('0'))
        }

    companion object {
        const val MAX_TOTAL_PRICE = 999999999
    }
}
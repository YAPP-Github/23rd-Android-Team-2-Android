package com.moneymong.moneymong.ledgermanual

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ui.isValidPaymentDate
import com.moneymong.moneymong.common.ui.isValidPaymentTime
import com.moneymong.moneymong.common.ui.validateValue
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
class LedgerManualViewModel @Inject constructor(
    private val postLedgerTransactionUseCase: PostLedgerTransactionUseCase,
    private val postFileUploadUseCase: PostFileUploadUseCase,
    private val fetchAgencyIdUseCase: FetchAgencyIdUseCase,
    private val fetchUserNicknameUseCase: FetchUserNicknameUseCase
) : BaseViewModel<LedgerManualState, LedgerManualSideEffect>(LedgerManualState()) {

    init {
        fetchUserInfo()
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
            val ledgerTransactionParam = LedgerTransactionParam(
                id = state.agencyId,
                storeInfo = state.storeNameValue.text,
                fundType = state.fundType,
                amount = state.totalPriceValue.text.toInt(),
                description = state.memoValue.text.ifEmpty { "내용 없음" },
                paymentDate = state.postPaymentDate,
                receiptImageUrls = state.receiptList,
                documentImageUrls = state.documentList
            )
            postLedgerTransactionUseCase(ledgerTransactionParam)
                .onSuccess {
                    postSideEffect(LedgerManualSideEffect.LedgerManualNavigateToLedger)
                }.onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

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

    fun onChangeImageType(isReceipt: Boolean) = intent {
        reduce { state.copy(isReceipt = isReceipt) }
        postSideEffect(LedgerManualSideEffect.LedgerManualOpenImagePicker)
    }

    fun onChangeFundType(fundType: FundType) = intent {
        reduce { state.copy(fundType = fundType) }
    }

    fun visiblePopBackStackModal(visible: Boolean) = intent {
        reduce { state.copy(showPopBackStackModal = visible) }
    }

    fun removeReceiptImage(image: String) = intent {
        reduce { state.copy(receiptList = state.receiptList - image) }
    }

    fun removeDocumentImage(image: String) = intent {
        reduce { state.copy(documentList = state.documentList - image) }
    }

    fun onClickPostTransaction() = eventEmit(LedgerManualSideEffect.LedgerManualPostTransaction)

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
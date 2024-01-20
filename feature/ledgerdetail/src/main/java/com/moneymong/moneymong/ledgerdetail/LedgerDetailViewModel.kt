package com.moneymong.moneymong.ledgerdetail

import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ext.toDateFormat
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.usecase.ledger.FetchLedgerTransactionDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LedgerDetailViewModel @Inject constructor(
    private val fetchLedgerTransactionDetailUseCase: FetchLedgerTransactionDetailUseCase
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

    private fun initTextValue(ledgerTransactionDetail: LedgerTransactionDetailEntity) = intent {
        reduce {
            state.copy(
                storeNameValue = state.storeNameValue.copy(text = ledgerTransactionDetail.storeInfo),
                totalPriceValue = state.totalPriceValue.copy(text = ledgerTransactionDetail.amount.toString()),
                paymentDateValue = state.paymentDateValue.copy(text = ledgerTransactionDetail.paymentDate.toDateFormat("yyyyMMdd")),
                paymentTimeValue = state.paymentDateValue.copy(text = ledgerTransactionDetail.paymentDate.toDateFormat("HHmmss")),
                memoValue = state.memoValue.copy(text = ledgerTransactionDetail.description)
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
package com.moneymong.moneymong.ledgermanual

import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.common.ui.isValidPaymentDate
import com.moneymong.moneymong.common.ui.isValidPaymentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class LedgerManualViewModel @Inject constructor(

): BaseViewModel<LedgerManualState, LedgerManualSideEffect>(LedgerManualState()) {

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
        val validate = validateValue(targetValue = value, length = 6, isDigit = true)
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
        val validate = validateValue(targetValue = value, length = 300)
        if (!validate) {
            reduce { state.copy(isMemoError = true) }
        } else {
            reduce { state.copy(isMemoError = false) }
        }
        reduce { state.copy(memoValue = value) }
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
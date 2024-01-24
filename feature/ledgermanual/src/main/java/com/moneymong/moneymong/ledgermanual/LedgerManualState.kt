package com.moneymong.moneymong.ledgermanual

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.common.ext.toZonedDateTime
import com.moneymong.moneymong.design_system.component.textfield.util.PriceType
import com.moneymong.moneymong.domain.param.ledger.FundType

data class LedgerManualState(
    val isLoading: Boolean = false,
    val storeNameValue: TextFieldValue = TextFieldValue(),
    val totalPriceValue: TextFieldValue = TextFieldValue(),
    val paymentDateValue: TextFieldValue = TextFieldValue(),
    val paymentTimeValue: TextFieldValue = TextFieldValue(),
    val memoValue: TextFieldValue = TextFieldValue(),
    val fundType: FundType = FundType.NONE,
    val receiptList: List<String> = emptyList(),
    val documentList: List<String> = emptyList(),
    val isStoreNameError: Boolean = false,
    val isTotalPriceError: Boolean = false,
    val isPaymentDateError: Boolean = false,
    val isPaymentTimeError: Boolean = false,
    val isMemoError: Boolean = false,
    val isReceipt: Boolean? = null,
    val showPopBackStackModal: Boolean = false
) : State {

    val enabled: Boolean
        get() {
            val isExpense = if (fundType == FundType.EXPENSE) receiptList.isNotEmpty() else true
            val hasStoreName = !isStoreNameError && storeNameValue.text.isNotEmpty()
            val hasTotalPrice = !isTotalPriceError && totalPriceValue.text.isNotEmpty()
            val hasPaymentDate = !isPaymentDateError && paymentDateValue.text.isNotEmpty()
            val hasPaymentTime = !isPaymentTimeError && paymentTimeValue.text.isNotEmpty()
            return hasStoreName && hasTotalPrice && hasPaymentDate && hasPaymentTime && !isMemoError && isExpense && fundType != FundType.NONE
        }

    val priceType: PriceType
        get() = when (fundType) {
            FundType.NONE -> PriceType.None
            FundType.INCOME -> PriceType.Income
            FundType.EXPENSE -> PriceType.Expense

        }

    val postPaymentDate: String
        get() = "${paymentDateValue.text} ${paymentTimeValue.text}".toZonedDateTime("yyyyMMdd HHmmss")
}

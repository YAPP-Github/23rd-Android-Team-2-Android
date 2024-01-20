package com.moneymong.moneymong.ledgerdetail

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.common.ext.toDateFormat
import com.moneymong.moneymong.common.ui.toWonFormat
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity

data class LedgerDetailState(
    val isLoading: Boolean = false,
    val useEditMode: Boolean = false,
    val enabled: Boolean = true,
    val storeNameValue: TextFieldValue = TextFieldValue(),
    val totalPriceValue: TextFieldValue = TextFieldValue(),
    val paymentDateValue: TextFieldValue = TextFieldValue(),
    val paymentTimeValue: TextFieldValue = TextFieldValue(),
    val memoValue: TextFieldValue = TextFieldValue(),
    val isStoreNameError: Boolean = false,
    val isTotalPriceError: Boolean = false,
    val isPaymentDateError: Boolean = false,
    val isPaymentTimeError: Boolean = false,
    val isMemoError: Boolean = false,
    val ledgerTransactionDetail: LedgerTransactionDetailEntity? = null
): State {

    val totalPrice: String
        get() = ledgerTransactionDetail?.amount?.let { it.toString().toWonFormat() } ?: "0"

    val formattedDate: String
        get() = ledgerTransactionDetail?.paymentDate?.let { it.toDateFormat("yyyy년 MM월 dd일") }.orEmpty()

    val formattedTime: String
        get() = ledgerTransactionDetail?.paymentDate?.let { it.toDateFormat("HH:mm:ss") }.orEmpty()
}

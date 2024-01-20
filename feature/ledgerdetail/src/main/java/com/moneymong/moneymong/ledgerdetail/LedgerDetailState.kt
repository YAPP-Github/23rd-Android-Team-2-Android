package com.moneymong.moneymong.ledgerdetail

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
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
): State

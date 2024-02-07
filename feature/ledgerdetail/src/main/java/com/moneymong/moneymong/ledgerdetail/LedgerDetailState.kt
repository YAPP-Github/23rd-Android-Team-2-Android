package com.moneymong.moneymong.ledgerdetail

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.common.ext.toDateFormat
import com.moneymong.moneymong.common.ui.toWonFormat
import com.moneymong.moneymong.design_system.component.textfield.util.PriceType
import com.moneymong.moneymong.domain.entity.ledgerdetail.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledger.FundType
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

data class LedgerDetailState(
    val isLoading: Boolean = true,
    val useEditMode: Boolean = false,
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
    val ledgerTransactionDetail: LedgerTransactionDetailEntity? = null,
    val receiptList: List<String> = emptyList(),
    val documentList: List<String> = emptyList(),
    val isReceipt: Boolean? = null,
    val receiptIdList: List<Int> = emptyList(),
    val documentIdList: List<Int> = emptyList(),
    val showConfirmModal: Boolean = false,
    val showErrorDialog: Boolean = false,
    val errorMessage: String = "",
    val isStaff: Boolean = false
) : State {

    val fundTypeText: String
        get() = ledgerTransactionDetail?.fundType?.let {
            if (it == FundType.INCOME.name) "수입" else "지출"
        }.orEmpty()

    val priceType: PriceType
        get() = ledgerTransactionDetail?.fundType?.let {
            if (it == FundType.INCOME.name) PriceType.Income else PriceType.Expense
        } ?: PriceType.None

    val totalPrice: String
        get() = ledgerTransactionDetail?.amount?.let { it.toString().toWonFormat() } ?: "0"

    val formattedDate: String
        get() = ledgerTransactionDetail?.paymentDate?.let { it.toDateFormat("yyyy년 MM월 dd일") }
            .orEmpty()

    val formattedTime: String
        get() = ledgerTransactionDetail?.paymentDate?.let { it.toDateFormat("HH:mm:ss") }.orEmpty()

    val formattedPaymentDate: String
        get() {
            val inputDateString = "${paymentDateValue.text}${paymentTimeValue.text}"
            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.KOREAN)
            val inputDateTime = LocalDateTime.parse(inputDateString, formatter)
            val offsetDateTime =
                OffsetDateTime.of(inputDateTime, ZoneOffset.ofHours(9)) // 9는 Asia/Seoul의 오프셋

            return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
        }

    val enabledEdit: Boolean
        get() {
            val hasStoreName = !isStoreNameError && storeNameValue.text.isNotEmpty()
            val hasTotalPrice = !isTotalPriceError && totalPriceValue.text.isNotEmpty()
            val hasPaymentDate = !isPaymentDateError && paymentDateValue.text.isNotEmpty()
            val hasPaymentTime = !isPaymentTimeError && paymentTimeValue.text.isNotEmpty()
            return hasStoreName && hasTotalPrice && hasPaymentDate && hasPaymentTime && !isMemoError
        }}

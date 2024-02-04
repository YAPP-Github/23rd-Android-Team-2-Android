package com.moneymong.moneymong.ocr_detail

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.design_system.component.textfield.util.PriceType
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentResultEntity
import com.moneymong.moneymong.domain.param.ledger.FundType
import java.io.File
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

data class OCRDetailState(
    val isLoading: Boolean = false,
    val document: DocumentEntity? = null,
    val receiptImage: String = "",
    val receiptFile: File? = null,
    val storeNameValue: TextFieldValue = TextFieldValue(),
    val totalPriceValue: TextFieldValue = TextFieldValue(),
    val paymentDateValue: TextFieldValue = TextFieldValue(),
    val paymentTimeValue: TextFieldValue = TextFieldValue(),
    val memoValue: TextFieldValue = TextFieldValue(text = "내용없음"),
    val fundType: FundType = FundType.EXPENSE,
    val receiptImageUrls: List<String> = emptyList(),
    val documentImageUrls: List<String> = listOf(""),
    val agencyId: Int = 0,
    val authorName: String = "",
    val isStoreNameError: Boolean = false,
    val isTotalPriceError: Boolean = false,
    val isPaymentDateError: Boolean = false,
    val isPaymentTimeError: Boolean = false,
    val isMemoError: Boolean = false
) : State {

    val receipt: DocumentResultEntity?
        get() = document?.images?.first()?.receipt?.result

    val postPaymentDate: String
        get() {
            val inputDateString = "${
                paymentDateValue.text.ifEmpty {
                    LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd", Locale.KOREAN))
                }
            }${paymentTimeValue.text.ifEmpty { "000000" }}"
            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.KOREAN)
            val inputDateTime = LocalDateTime.parse(inputDateString, formatter)
            val offsetDateTime =
                OffsetDateTime.of(inputDateTime, ZoneOffset.ofHours(9)) // 9는 Asia/Seoul의 오프셋

            return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
        }

    val enabled: Boolean
        get() = storeNameValue.text.isNotEmpty() &&
                totalPriceValue.text.isNotEmpty() &&
                !isStoreNameError &&
                !isTotalPriceError &&
                !isPaymentDateError &&
                !isPaymentTimeError &&
                !isMemoError

    val priceType: PriceType =
        when (fundType) {
            FundType.EXPENSE -> PriceType.Expense
            FundType.INCOME -> PriceType.Income
            FundType.NONE -> PriceType.None
        }
}

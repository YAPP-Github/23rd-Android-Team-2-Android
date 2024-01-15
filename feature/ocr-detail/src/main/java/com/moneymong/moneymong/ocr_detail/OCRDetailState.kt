package com.moneymong.moneymong.ocr_detail

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
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
): State {

    val receipt: DocumentResultEntity?
        get() = document?.images?.first()?.receipt?.result

    val postPaymentDate: String
        get() {
            val inputDateString = "${paymentDateValue.text} ${paymentTimeValue.text}"
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss", Locale.KOREAN)
            val inputDateTime = LocalDateTime.parse(inputDateString, formatter)
            val offsetDateTime = OffsetDateTime.of(inputDateTime, ZoneOffset.ofHours(9)) // 9는 Asia/Seoul의 오프셋

            return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
        }
}

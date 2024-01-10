package com.moneymong.moneymong.ocr_result

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.common.ui.toWonFormat
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentResultEntity
import java.io.File
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

data class OCRResultState(
    val isLoading: Boolean = false,
    val receiptImage: String = "",
    val receiptFile: File? = null,
    val document: DocumentEntity? = null,
    val memo: String = "내용 없음"
) : State {

    val receipt: DocumentResultEntity?
        get() = document?.images?.first()?.receipt?.result

    val visibleSnackbar: Boolean
        get() {
            val hasSource = receipt?.storeInfo?.name?.text.orEmpty().isNotEmpty()
            val hasPrice = receipt?.totalPrice?.price?.text.orEmpty().isNotEmpty()
            val hasDate = receipt?.paymentInfo?.date?.text.orEmpty().isNotEmpty()
            val hasTime = receipt?.paymentInfo?.time?.text.orEmpty().isNotEmpty()

            return !hasSource || !hasPrice || !hasDate || !hasTime
        }

    val btnEnabled: Boolean
        get() {
            val hasSource = receipt?.storeInfo?.name?.text.orEmpty().isNotEmpty()
            val hasPrice = receipt?.totalPrice?.price?.text.orEmpty().isNotEmpty()

            return hasSource && hasPrice
        }

    val formattedPrice: String
        get() = receipt?.totalPrice?.price?.formatted?.value.orEmpty().toWonFormat(true)

    val formattedDate: String
        get() {
            val date = receipt?.paymentInfo?.date?.formatted
            val formattedYear = date?.year?.let { "${it}년" }.orEmpty()
            val formattedMonth = date?.month?.let { "${it}월" }.orEmpty()
            val formattedDay = date?.day?.let { "${it}일" }.orEmpty()

            return "$formattedYear $formattedMonth $formattedDay"
        }

    val formattedTime: String
        get() {
            val time = receipt?.paymentInfo?.time?.formatted
            return "${time?.hour ?: "00"}:${time?.minute ?: "00"}:${time?.second ?: "00"}"
        }

    val postPaymentDate: String
        get() {
            val inputDateString = "$formattedDate $formattedTime"
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss", Locale.KOREAN)
            val inputDateTime = LocalDateTime.parse(inputDateString, formatter)
            val offsetDateTime = OffsetDateTime.of(inputDateTime, ZoneOffset.ofHours(9)) // 9는 Asia/Seoul의 오프셋

            return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
        }
}

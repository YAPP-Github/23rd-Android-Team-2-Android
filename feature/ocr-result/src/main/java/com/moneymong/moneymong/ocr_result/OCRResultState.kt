package com.moneymong.moneymong.ocr_result

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.common.ext.toZonedDateTime
import com.moneymong.moneymong.common.ui.toWonFormat
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.entity.ocr.DocumentResultEntity
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class OCRResultState(
    val isLoading: Boolean = false,
    val receiptImage: String = "",
    val receiptFile: File? = null,
    val document: DocumentEntity? = null,
    val memo: String = "내용 없음",
    val agencyId: Int = 0
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
            val currentDate = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(Date(System.currentTimeMillis()))
            return receipt?.paymentInfo?.date?.let {
                "${it.formatted?.year}년 ${it.formatted?.month}월 ${it.formatted?.day}일"
            } ?: currentDate
        }

    val formattedTime: String
        get() {
            val currentTime = SimpleDateFormat("HH:mm:ss", Locale.KOREA).format(Date(System.currentTimeMillis()))
            return receipt?.paymentInfo?.time?.let {
                "${it.formatted?.hour}:${it.formatted?.minute}:${it.formatted?.second}"
            } ?: currentTime
        }

    val postPaymentDate: String
        get() = "$formattedDate $formattedTime".toZonedDateTime()
}

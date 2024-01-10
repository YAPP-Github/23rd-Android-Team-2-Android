package com.moneymong.moneymong.ocr_detail

import androidx.compose.ui.text.input.TextFieldValue
import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.param.ledger.FundType

data class OCRDetailState(
    val document: DocumentEntity? = null,
    val receiptImage: String = "",
    val storeNameValue: TextFieldValue = TextFieldValue(),
    val totalPriceValue: TextFieldValue = TextFieldValue(),
    val paymentDateValue: TextFieldValue = TextFieldValue(),
    val paymentTimeValue: TextFieldValue = TextFieldValue(),
    val memoValue: TextFieldValue = TextFieldValue(text = "내용없음"),
    val fundType: FundType = FundType.EXPENSE,
): State

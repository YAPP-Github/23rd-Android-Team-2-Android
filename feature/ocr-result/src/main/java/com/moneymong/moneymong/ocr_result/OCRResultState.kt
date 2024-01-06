package com.moneymong.moneymong.ocr_result

import com.moneymong.moneymong.common.base.State

data class OCRResultState(
    val receiptImage: String? = null
): State

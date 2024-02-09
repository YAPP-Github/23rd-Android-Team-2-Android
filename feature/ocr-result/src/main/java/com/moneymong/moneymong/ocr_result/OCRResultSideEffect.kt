package com.moneymong.moneymong.ocr_result

import com.moneymong.moneymong.common.base.SideEffect
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity

sealed class OCRResultSideEffect : SideEffect {
    data object OCRResultShowSnackbar : OCRResultSideEffect()
    data object OCRResultNavigateToLedger : OCRResultSideEffect()
    data class OCRResultNavigateToOCRDetail(val document: DocumentEntity?) : OCRResultSideEffect()
}

package com.moneymong.moneymong.ocr

import com.moneymong.moneymong.common.base.SideEffect
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity

sealed class OCRSideEffect : SideEffect {
    data object OCRTakePicture : OCRSideEffect()
    data object OCRMoveToPermissionSetting : OCRSideEffect()
    data class OCRNavigateToOCRResult(val document: DocumentEntity?) : OCRSideEffect()
    data class OCRPostDocumentApi(val base64: String) : OCRSideEffect()
}

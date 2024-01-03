package com.moneymong.moneymong.ocr

import com.moneymong.moneymong.common.base.SideEffect

sealed class OCRSideEffect : SideEffect {
    data object OCRTakePicture : OCRSideEffect()
    data object OCRMoveToPermissionSetting : OCRSideEffect()
    data class OCRPostDocumentApi(val base64: String) : OCRSideEffect()
}

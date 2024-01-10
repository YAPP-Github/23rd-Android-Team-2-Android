package com.moneymong.moneymong.ocr_result

import com.moneymong.moneymong.common.base.SideEffect

sealed class OCRResultSideEffect : SideEffect {
    data object OCRResultShowSnackbar : OCRResultSideEffect()
    data object OCRResultNavigateToHome : OCRResultSideEffect()
}

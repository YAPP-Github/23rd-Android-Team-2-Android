package com.moneymong.moneymong.ocr

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.ocr.util.ModalType

data class OCRState(
    val modalType: ModalType = ModalType.CameraPermission,
    val showPermissionDialog: Boolean = false,
): State

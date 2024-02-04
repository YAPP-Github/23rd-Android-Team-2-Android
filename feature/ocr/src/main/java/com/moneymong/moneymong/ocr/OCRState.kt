package com.moneymong.moneymong.ocr

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.ocr.util.ModalType

data class OCRState(
    val document: DocumentEntity? = null,
    val modalType: ModalType = ModalType.CameraPermission,
    val permissionDialogStatus: Boolean = false,
    val isDeniedCamera: Boolean = false,
    val visibleHelper: Boolean = true,
    val isLoading: Boolean = false
): State {

    val isReceipt: Boolean
        get() {
            val result = document?.images?.first()?.receipt?.result
            val storeName = result?.storeInfo?.name?.text.orEmpty().isNotEmpty()
            val totalPrice = result?.totalPrice?.price?.text.orEmpty().isNotEmpty()
            val date = result?.paymentInfo?.date?.text.orEmpty().isNotEmpty()
            val time = result?.paymentInfo?.time?.text.orEmpty().isNotEmpty()

            return storeName || totalPrice || date || time
        }

    val showPermissionDialog: Boolean
        get() = permissionDialogStatus && !isDeniedCamera
}

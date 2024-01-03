package com.moneymong.moneymong.ocr

import android.util.Log
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.domain.usecase.ocr.DocumentOCRUseCase
import com.moneymong.moneymong.ocr.util.ModalType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OCRViewModel @Inject constructor(
    private val documentOCRUseCase: DocumentOCRUseCase
) : BaseViewModel<OCRState, OCRSideEffect>(OCRState()) {

    fun postDocumentOCR(receiptImage: String) = intent {
        reduce { state.copy(isLoading = true) }
        val documentParam = DocumentParam(
            requestId = "moneymong",
            timestamp = System.currentTimeMillis(),
            images = listOf(
                DocumentParam.DocumentImageParam(
                    data = receiptImage,
                    name = "moneymong_receipt"
                )
            )
        )
        val a = documentOCRUseCase(documentParam)
//            .onSuccess {
//                it
//            }.onFailure {
//                it
//            }
            .also { reduce { state.copy(isLoading = false) } }
        Log.d("OCR", a.toString())
    }

    fun onClickTakePicture() = eventEmit(OCRSideEffect.OCRTakePicture)

    fun visiblePermissionDialog(hasPermission: Boolean) = intent {
        reduce { state.copy(showPermissionDialog = !hasPermission) }
    }

    fun onClickDialogPositive() = intent {
        eventEmit(OCRSideEffect.OCRMoveToPermissionSetting)
        reduce {
            state.copy(showPermissionDialog = false)
        }
    }

    fun onClickDialogNegative() = intent {
        when (state.modalType) {
            ModalType.CameraPermission -> {
                reduce { state.copy(showPermissionDialog = false) }
            }

            ModalType.GalleryPermission -> {
                reduce { state.copy(showPermissionDialog = false) }
            }
        }
    }
}
package com.moneymong.moneymong.ocr

import android.content.SharedPreferences
import androidx.core.content.edit
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.param.ocr.DocumentParam
import com.moneymong.moneymong.domain.usecase.ocr.DocumentOCRUseCase
import com.moneymong.moneymong.domain.usecase.user.FetchDeniedCameraPermissionUseCase
import com.moneymong.moneymong.domain.usecase.user.SaveDeniedCameraPermissionUseCase
import com.moneymong.moneymong.ocr.util.ModalType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OCRViewModel @Inject constructor(
    private val documentOCRUseCase: DocumentOCRUseCase,
    private val saveDeniedCameraPermissionUseCase: SaveDeniedCameraPermissionUseCase,
    private val fetchDeniedCameraPermissionUseCase: FetchDeniedCameraPermissionUseCase,
    private val prefs: SharedPreferences
) : BaseViewModel<OCRState, OCRSideEffect>(OCRState()) {

    init {
        fetchDeniedCameraPermission()
    }

    fun postDocumentOCR(receiptImage: String) = intent {
        if (!state.isLoading) {
            savedReceiptImage(receiptImage)
            reduce {
                state.copy(isLoading = true)
            }

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
            documentOCRUseCase(documentParam)
                .onSuccess {
                    reduce { state.copy(document = it) }
                    possibleNavigateToOCRResult(it.images.first().inferResult.orEmpty())
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    @OptIn(OrbitExperimental::class)
    fun saveDeniedCameraPermission(isDenied: Boolean) = blockingIntent {
        saveDeniedCameraPermissionUseCase(isDenied)
    }

    @OptIn(OrbitExperimental::class)
    fun fetchDeniedCameraPermission() = blockingIntent {
        val isDeniedCamera = fetchDeniedCameraPermissionUseCase(Unit)
        reduce { state.copy(isDeniedCamera = isDeniedCamera) }
    }

    private fun possibleNavigateToOCRResult(message: String) = intent {
        val receiptSuccess = message == "SUCCESS" && state.isReceipt
        if (receiptSuccess) {
            postSideEffect(OCRSideEffect.OCRNavigateToOCRResult(state.document))
        } else {
            // TODO
        }
    }

    private fun savedReceiptImage(image: String) = blockingIntent {
        prefs.edit { putString("receiptImage", image).apply() }
    }

    // onClick
    fun visiblePermissionDialog(hasPermission: Boolean) = intent {
        reduce { state.copy(permissionDialogStatus = !hasPermission) }
    }

    fun onClickDialogPositive() = intent {
        eventEmit(OCRSideEffect.OCRMoveToPermissionSetting)
        reduce {
            state.copy(permissionDialogStatus = false)
        }
    }

    fun onClickDialogNegative() = intent {
        when (state.modalType) {
            ModalType.CameraPermission -> {
                saveDeniedCameraPermission(true)
                reduce {
                    state.copy(
                        permissionDialogStatus = false,
                        isDeniedCamera = true
                    )
                }
            }

            ModalType.GalleryPermission -> {
                reduce { state.copy(permissionDialogStatus = false) }
            }
        }
    }

    fun onClickHelper() = intent {
        reduce { state.copy(visibleHelper = !state.visibleHelper) }
    }
}
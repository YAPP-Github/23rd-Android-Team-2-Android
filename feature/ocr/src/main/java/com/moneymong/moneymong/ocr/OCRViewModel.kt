package com.moneymong.moneymong.ocr

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.ocr.util.ModalType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OCRViewModel @Inject constructor(

) : BaseViewModel<OCRState, OCRSideEffect>(OCRState()) { // TODO Hilt

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
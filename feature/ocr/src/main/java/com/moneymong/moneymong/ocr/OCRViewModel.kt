package com.moneymong.moneymong.ocr

import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OCRViewModel @Inject constructor(

) : BaseViewModel<OCRState, OCRSideEffect>(OCRState()){ // TODO Hilt

    fun onClickTakePicture() = eventEmit(OCRSideEffect.OCRTakePicture)
}
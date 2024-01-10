package com.moneymong.moneymong.ocr_detail

import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OCRDetailViewModel @Inject constructor(

): BaseViewModel<OCRDetailState, OCRDetailSideEffect>(OCRDetailState()) {

}
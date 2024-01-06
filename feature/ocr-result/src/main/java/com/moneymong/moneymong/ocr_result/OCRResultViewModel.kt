package com.moneymong.moneymong.ocr_result

import android.content.SharedPreferences
import com.moneymong.moneymong.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OCRResultViewModel @Inject constructor(
    private val prefs: SharedPreferences
): BaseViewModel<OCRResultState, OCRResultSideEffect>(OCRResultState()) {

    init {
        fetchReceiptImage()
    }

    private fun fetchReceiptImage() = blockingIntent {
        val receiptImage = prefs.getString("receiptImage", "")
        reduce { state.copy(receiptImage = receiptImage) }
    }
}
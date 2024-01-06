package com.moneymong.moneymong.ocr_result

import android.content.SharedPreferences
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class OCRResultViewModel @Inject constructor(
    private val prefs: SharedPreferences
): BaseViewModel<OCRResultState, OCRResultSideEffect>(OCRResultState()) {

    init {
        fetchReceiptImage()
    }

    fun updateDocument(document: DocumentEntity?) = intent {
        reduce { state.copy(document = document) }

        if (state.visibleSnackbar) {
            postSideEffect(OCRResultSideEffect.OCRResultShowSnackbar)
        }
    }

    private fun fetchReceiptImage() = blockingIntent {
        val receiptImage = prefs.getString("receiptImage", "")
        reduce { state.copy(receiptImage = receiptImage) }
    }
}
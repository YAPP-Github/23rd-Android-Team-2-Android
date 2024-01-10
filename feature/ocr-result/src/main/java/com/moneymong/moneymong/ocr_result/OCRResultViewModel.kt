package com.moneymong.moneymong.ocr_result

import android.content.SharedPreferences
import com.moneymong.moneymong.common.base.BaseViewModel
import com.moneymong.moneymong.domain.entity.ocr.DocumentEntity
import com.moneymong.moneymong.domain.param.ledger.FundType
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.param.ocr.FileUploadParam
import com.moneymong.moneymong.domain.usecase.ledger.PostLedgerTransactionUseCase
import com.moneymong.moneymong.domain.usecase.ocr.PostFileUploadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OCRResultViewModel @Inject constructor(
    private val prefs: SharedPreferences,
    private val postLedgerTransactionUseCase: PostLedgerTransactionUseCase,
    private val postFileUploadUseCase: PostFileUploadUseCase
) : BaseViewModel<OCRResultState, OCRResultSideEffect>(OCRResultState()) {

    init {
        fetchReceiptImage()
    }

    fun postLedgerTransaction(s3Url: String) = intent {
        if (!state.isLoading) {
            reduce { state.copy(isLoading = true) }
            val ledgerTransactionParam = LedgerTransactionParam(
                id = 1,
                storeInfo = state.receipt?.storeInfo?.name?.text.orEmpty(),
                fundType = FundType.INCOME,
                amount = state.receipt?.totalPrice?.price?.formatted?.value.orEmpty().toInt(),
                description = state.memo,
                paymentDate = state.postPaymentDate,
                receiptImageUrls = listOf(s3Url)
            )
            postLedgerTransactionUseCase(ledgerTransactionParam)
                .onSuccess {
                    postSideEffect(OCRResultSideEffect.OCRResultNavigateToHome)
                }.onFailure {
                    // TODO
                }.also { reduce { state.copy(isLoading = false) } }
        }
    }

    fun postReceiptImage() = intent {
        state.receiptFile?.let {
            if (!state.isLoading) {
                reduce { state.copy(isLoading = true) }
                val file = FileUploadParam(it, "ocr")
                postFileUploadUseCase(file)
                    .onSuccess {
                        postLedgerTransaction(it.path)
                    }.onFailure {
                        // TODO
                    }.also { reduce { state.copy(isLoading = false) } }
            }
        }
    }

    fun updateDocument(document: DocumentEntity?) = intent {
        reduce { state.copy(document = document) }

        if (state.visibleSnackbar) {
            postSideEffect(OCRResultSideEffect.OCRResultShowSnackbar)
        }
    }

    fun reduceReceiptFile(file: File?) = intent {
        reduce { state.copy(receiptFile = file) }
    }

    private fun fetchReceiptImage() = blockingIntent {
        val receiptImage = prefs.getString("receiptImage", "").orEmpty()
        reduce { state.copy(receiptImage = receiptImage) }
    }

    // onClick
    fun onClickOCREdit() = intent {
        postSideEffect(OCRResultSideEffect.OCRResultNavigateToOCRDetail(state.document))
    }
}
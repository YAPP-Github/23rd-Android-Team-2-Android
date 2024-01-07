package com.moneymong.moneymong.data.datasource.ledger

import com.moneymong.moneymong.network.request.ocr.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ocr.LedgerTransactionResponse

interface LedgerRemoteDataSource {
    suspend fun postLedgerTransaction(id: Int, body: LedgerTransactionRequest): Result<LedgerTransactionResponse>
}
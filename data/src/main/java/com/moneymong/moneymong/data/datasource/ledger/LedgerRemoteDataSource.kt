package com.moneymong.moneymong.data.datasource.ledger

import com.moneymong.moneymong.network.request.ledger.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionResponse

interface LedgerRemoteDataSource {
    suspend fun postLedgerTransaction(id: Int, body: LedgerTransactionRequest): Result<LedgerTransactionResponse>
}
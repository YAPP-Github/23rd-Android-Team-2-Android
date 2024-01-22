package com.moneymong.moneymong.data.datasource.ledgerdetail

import com.moneymong.moneymong.network.request.ledger.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledger.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledger.LedgerTransactionDetailRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionDetailResponse

interface LedgerDetailRemoteDataSource {
    suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailResponse>
    suspend fun postLedgerReceiptTransaction(detailId: Int, body: LedgerReceiptRequest): Result<Unit>
    suspend fun postLedgerDocumentTransaction(detailId: Int, body: LedgerDocumentRequest): Result<Unit>
    suspend fun updateLedgerTransactionDetail(detailId: Int, body: LedgerTransactionDetailRequest): Result<LedgerTransactionDetailResponse>
    suspend fun deleteLedgerReceiptTransaction(detailId: Int, receiptId: Int): Result<Unit>
    suspend fun deleteLedgerDocumentTransaction(detailId: Int, documentId: Int): Result<Unit>
}
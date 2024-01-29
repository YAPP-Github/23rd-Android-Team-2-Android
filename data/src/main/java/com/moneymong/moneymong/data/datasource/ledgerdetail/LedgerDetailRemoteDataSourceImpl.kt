package com.moneymong.moneymong.data.datasource.ledgerdetail

import com.moneymong.moneymong.network.api.LedgerDetailApi
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerTransactionDetailRequest
import com.moneymong.moneymong.network.response.ledgerdetail.LedgerTransactionDetailResponse
import javax.inject.Inject

class LedgerDetailRemoteDataSourceImpl @Inject constructor(
    private val ledgerDetailApi: LedgerDetailApi
) : LedgerDetailRemoteDataSource {
    override suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailResponse> =
        ledgerDetailApi.fetchLedgerTransactionDetail(detailId = detailId)

    override suspend fun postLedgerReceiptTransaction(detailId: Int, body: LedgerReceiptRequest): Result<Unit> =
        ledgerDetailApi.postLedgerReceiptTransaction(detailId = detailId, body = body)

    override suspend fun postLedgerDocumentTransaction(detailId: Int, body: LedgerDocumentRequest): Result<Unit> =
        ledgerDetailApi.postLedgerDocumentTransaction(detailId = detailId, body = body)

    override suspend fun updateLedgerTransactionDetail(
        detailId: Int,
        body: LedgerTransactionDetailRequest
    ): Result<LedgerTransactionDetailResponse> =
        ledgerDetailApi.updateLedgerTransactionDetail(detailId = detailId, body = body)

    override suspend fun deleteLedgerReceiptTransaction(
        detailId: Int,
        receiptId: Int
    ): Result<Unit> =
        ledgerDetailApi.deleteLedgerReceiptTransaction(detailId = detailId, receiptId = receiptId)

    override suspend fun deleteLedgerDocumentTransaction(
        detailId: Int,
        documentId: Int
    ): Result<Unit> =
        ledgerDetailApi.deleteLedgerDocumentTransaction(detailId = detailId, documentId = documentId)

    override suspend fun deleteLedgerDetail(detailId: Int): Result<Unit> =
        ledgerDetailApi.deleteLedgerDetail(detailId = detailId)
}
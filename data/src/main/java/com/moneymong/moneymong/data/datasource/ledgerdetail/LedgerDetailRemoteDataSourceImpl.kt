package com.moneymong.moneymong.data.datasource.ledgerdetail

import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledgerdetail.LedgerTransactionDetailRequest
import com.moneymong.moneymong.network.response.ledgerdetail.LedgerTransactionDetailResponse
import javax.inject.Inject

class LedgerDetailRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
) : LedgerDetailRemoteDataSource {
    override suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailResponse> =
        moneyMongApi.fetchLedgerTransactionDetail(detailId = detailId)

    override suspend fun postLedgerReceiptTransaction(detailId: Int, body: LedgerReceiptRequest): Result<Unit> =
        moneyMongApi.postLedgerReceiptTransaction(detailId = detailId, body = body)

    override suspend fun postLedgerDocumentTransaction(detailId: Int, body: LedgerDocumentRequest): Result<Unit> =
        moneyMongApi.postLedgerDocumentTransaction(detailId = detailId, body = body)

    override suspend fun updateLedgerTransactionDetail(
        detailId: Int,
        body: LedgerTransactionDetailRequest
    ): Result<LedgerTransactionDetailResponse> =
        moneyMongApi.updateLedgerTransactionDetail(detailId = detailId, body = body)

    override suspend fun deleteLedgerReceiptTransaction(
        detailId: Int,
        receiptId: Int
    ): Result<Unit> =
        moneyMongApi.deleteLedgerReceiptTransaction(detailId = detailId, receiptId = receiptId)

    override suspend fun deleteLedgerDocumentTransaction(
        detailId: Int,
        documentId: Int
    ): Result<Unit> =
        moneyMongApi.deleteLedgerDocumentTransaction(detailId = detailId, documentId = documentId)

    override suspend fun deleteLedgerDetail(detailId: Int): Result<Unit> =
        moneyMongApi.deleteLedgerDetail(detailId = detailId)
}
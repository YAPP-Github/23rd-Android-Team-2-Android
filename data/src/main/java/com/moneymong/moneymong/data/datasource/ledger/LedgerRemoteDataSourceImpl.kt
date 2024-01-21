package com.moneymong.moneymong.data.datasource.ledger

import com.moneymong.moneymong.network.api.MoneyMongApi
import com.moneymong.moneymong.network.request.ledger.LedgerDocumentRequest
import com.moneymong.moneymong.network.request.ledger.LedgerReceiptRequest
import com.moneymong.moneymong.network.request.ledger.LedgerTransactionDetailRequest
import com.moneymong.moneymong.network.request.ledger.LedgerTransactionRequest
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionDetailResponse
import com.moneymong.moneymong.network.response.ledger.LedgerTransactionListResponse
import javax.inject.Inject

class LedgerRemoteDataSourceImpl @Inject constructor(
    private val moneyMongApi: MoneyMongApi
): LedgerRemoteDataSource {
    override suspend fun fetchLedgerTransactionList(
        id: Int,
        year: Int,
        month: Int,
        page: Int,
        limit: Int
    ): Result<LedgerTransactionListResponse> =
        moneyMongApi.fetchLedgerTransactionList(
            id = id,
            year = year,
            month = month,
            page = page,
            limit = limit
        )

    override suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean> =
        moneyMongApi.fetchAgencyExistLedger(agencyId = agencyId)

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

    override suspend fun postLedgerTransaction(id: Int, body: LedgerTransactionRequest) =
        moneyMongApi.postLedgerTransaction(id = id, body = body)
}
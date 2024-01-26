package com.moneymong.moneymong.data.repository.ledgerdetail

import com.moneymong.moneymong.data.datasource.ledgerdetail.LedgerDetailRemoteDataSource
import com.moneymong.moneymong.data.mapper.ledgerdetail.toEntity
import com.moneymong.moneymong.data.mapper.ledgerdetail.toRequest
import com.moneymong.moneymong.domain.entity.ledgerdetail.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class LedgerDetailRepositoryImpl @Inject constructor(
    private val ledgerDetailRemoteDataSource: LedgerDetailRemoteDataSource
) : LedgerDetailRepository {
    override suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailEntity> =
        ledgerDetailRemoteDataSource.fetchLedgerTransactionDetail(detailId = detailId)
            .map { it.toEntity() }

    override suspend fun postLedgerReceiptTransaction(body: LedgerReceiptParam): Result<Unit> =
        ledgerDetailRemoteDataSource.postLedgerReceiptTransaction(
            detailId = body.detailId,
            body = body.toRequest()
        )

    override suspend fun postLedgerDocumentTransaction(body: LedgerDocumentParam): Result<Unit> =
        ledgerDetailRemoteDataSource.postLedgerDocumentTransaction(
            detailId = body.detailId,
            body = body.toRequest()
        )

    override suspend fun updateLedgerTransactionDetail(body: LedgerTransactionDetailParam): Result<LedgerTransactionDetailEntity> =
        ledgerDetailRemoteDataSource.updateLedgerTransactionDetail(
            detailId = body.detailId,
            body = body.toRequest()
        ).map { it.toEntity() }

    override suspend fun deleteLedgerReceiptTransaction(deleteLedgerReceiptParam: DeleteLedgerReceiptParam): Result<Unit> =
        ledgerDetailRemoteDataSource.deleteLedgerReceiptTransaction(
            detailId = deleteLedgerReceiptParam.detailId,
            receiptId = deleteLedgerReceiptParam.receiptId
        )

    override suspend fun deleteLedgerDocumentTransaction(deleteLedgerDocumentParam: DeleteLedgerDocumentParam): Result<Unit> =
        ledgerDetailRemoteDataSource.deleteLedgerDocumentTransaction(
            detailId = deleteLedgerDocumentParam.detailId,
            documentId = deleteLedgerDocumentParam.documentId
        )

    override suspend fun deleteLedgerDetail(detailId: Int): Result<Unit> =
        ledgerDetailRemoteDataSource.deleteLedgerDetail(detailId = detailId)
}
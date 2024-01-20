package com.moneymong.moneymong.data.repository.ledger

import com.moneymong.moneymong.data.datasource.ledger.LedgerRemoteDataSource
import com.moneymong.moneymong.data.mapper.ledger.toEntity
import com.moneymong.moneymong.data.mapper.ledger.toRequest
import com.moneymong.moneymong.data.mapper.ledgerdetail.toRequest
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class LedgerRepositoryImpl @Inject constructor(
    private val ledgerRemoteDataSource: LedgerRemoteDataSource
) : LedgerRepository {
    override suspend fun fetchLedgerTransactionList(param: LedgerTransactionListParam): Result<LedgerTransactionListEntity> =
        ledgerRemoteDataSource.fetchLedgerTransactionList(
            id = param.id,
            year = param.year,
            month = param.month,
            page = param.page,
            limit = param.limit
        ).map { it.toEntity() }

    override suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean> =
        ledgerRemoteDataSource.fetchAgencyExistLedger(agencyId = agencyId)

    override suspend fun postLedgerTransaction(param: LedgerTransactionParam): Result<LedgerTransactionEntity> =
        ledgerRemoteDataSource.postLedgerTransaction(id = param.id, body = param.toRequest())
            .map { it.toEntity() }

    override suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailEntity> =
        ledgerRemoteDataSource.fetchLedgerTransactionDetail(detailId = detailId)
            .map { it.toEntity() }

    override suspend fun postLedgerReceiptTransaction(body: LedgerReceiptParam): Result<Unit> =
        ledgerRemoteDataSource.postLedgerReceiptTransaction(body = body.toRequest())

    override suspend fun postLedgerDocumentTransaction(body: LedgerDocumentParam): Result<Unit> =
        ledgerRemoteDataSource.postLedgerDocumentTransaction(body = body.toRequest())

    override suspend fun updateLedgerTransactionDetail(body: LedgerTransactionDetailParam): Result<LedgerTransactionDetailEntity> =
        ledgerRemoteDataSource.updateLedgerTransactionDetail(
            detailId = body.detailId,
            body = body.toRequest()
        ).map { it.toEntity() }

    override suspend fun deleteLedgerReceiptTransaction(deleteLedgerReceiptParam: DeleteLedgerReceiptParam): Result<Unit> =
        ledgerRemoteDataSource.deleteLedgerReceiptTransaction(
            detailId = deleteLedgerReceiptParam.detailId,
            receiptId = deleteLedgerReceiptParam.receiptId
        )

    override suspend fun deleteLedgerDocumentTransaction(deleteLedgerDocumentParam: DeleteLedgerDocumentParam): Result<Unit> =
        ledgerRemoteDataSource.deleteLedgerDocumentTransaction(
            detailId = deleteLedgerDocumentParam.detailId,
            documentId = deleteLedgerDocumentParam.documentId
        )
}
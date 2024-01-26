package com.moneymong.moneymong.domain.repository.ledgerdetail

import com.moneymong.moneymong.domain.entity.ledgerdetail.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam

interface LedgerDetailRepository {
    suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailEntity>
    suspend fun postLedgerReceiptTransaction(body: LedgerReceiptParam): Result<Unit>
    suspend fun postLedgerDocumentTransaction(body: LedgerDocumentParam): Result<Unit>
    suspend fun updateLedgerTransactionDetail(body: LedgerTransactionDetailParam): Result<LedgerTransactionDetailEntity>
    suspend fun deleteLedgerReceiptTransaction(deleteLedgerReceiptParam: DeleteLedgerReceiptParam): Result<Unit>
    suspend fun deleteLedgerDocumentTransaction(deleteLedgerDocumentParam: DeleteLedgerDocumentParam): Result<Unit>
    suspend fun deleteLedgerDetail(detailId: Int): Result<Unit>
}
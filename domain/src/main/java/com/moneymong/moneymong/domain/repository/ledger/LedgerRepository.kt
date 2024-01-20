package com.moneymong.moneymong.domain.repository.ledger

import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam

interface LedgerRepository {
    suspend fun fetchLedgerTransactionList(param: LedgerTransactionListParam): Result<LedgerTransactionListEntity>
    suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean>
    suspend fun postLedgerTransaction(param: LedgerTransactionParam): Result<LedgerTransactionEntity>
    suspend fun fetchLedgerTransactionDetail(detailId: Int): Result<LedgerTransactionDetailEntity>
    suspend fun postLedgerReceiptTransaction(body: LedgerReceiptParam): Result<Unit>
    suspend fun postLedgerDocumentTransaction(body: LedgerDocumentParam): Result<Unit>
    suspend fun updateLedgerTransactionDetail(body: LedgerTransactionDetailParam): Result<LedgerTransactionDetailEntity>
    suspend fun deleteLedgerReceiptTransaction(deleteLedgerReceiptParam: DeleteLedgerReceiptParam): Result<Unit>
    suspend fun deleteLedgerDocumentTransaction(deleteLedgerDocumentParam: DeleteLedgerDocumentParam): Result<Unit>
}
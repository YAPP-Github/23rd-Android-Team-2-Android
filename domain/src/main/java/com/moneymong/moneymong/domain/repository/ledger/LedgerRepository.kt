package com.moneymong.moneymong.domain.repository.ledger

import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam

interface LedgerRepository {
    suspend fun fetchLedgerTransactionList(param: LedgerTransactionListParam): Result<LedgerTransactionListEntity>
    suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean>
    suspend fun postLedgerTransaction(param: LedgerTransactionParam): Result<LedgerTransactionEntity>
}
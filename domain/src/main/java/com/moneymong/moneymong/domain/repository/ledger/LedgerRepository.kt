package com.moneymong.moneymong.domain.repository.ledger

import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam

interface LedgerRepository {
    suspend fun postLedgerTransaction(param: LedgerTransactionParam): Result<LedgerTransactionEntity>
}
package com.moneymong.moneymong.domain.usecase.ledger

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class PostLedgerTransactionUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
): BaseUseCase<LedgerTransactionParam, Result<LedgerTransactionEntity>>() {
    override suspend fun invoke(data: LedgerTransactionParam): Result<LedgerTransactionEntity> =
        ledgerRepository.postLedgerTransaction(data)
}
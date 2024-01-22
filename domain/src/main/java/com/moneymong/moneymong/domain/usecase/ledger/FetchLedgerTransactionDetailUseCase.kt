package com.moneymong.moneymong.domain.usecase.ledger

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class FetchLedgerTransactionDetailUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
): BaseUseCase<Int, Result<LedgerTransactionDetailEntity>>() {
    override suspend fun invoke(data: Int): Result<LedgerTransactionDetailEntity> =
        ledgerRepository.fetchLedgerTransactionDetail(data)
}
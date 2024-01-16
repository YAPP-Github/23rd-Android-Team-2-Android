package com.moneymong.moneymong.domain.usecase.ledger

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class FetchLedgerTransactionListUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
) : BaseUseCase<LedgerTransactionListParam, Result<LedgerTransactionListEntity>>() {
    override suspend fun invoke(data: LedgerTransactionListParam): Result<LedgerTransactionListEntity> =
        ledgerRepository.fetchLedgerTransactionList(data)
}
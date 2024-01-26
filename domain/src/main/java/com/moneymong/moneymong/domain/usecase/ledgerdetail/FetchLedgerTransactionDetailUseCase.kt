package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ledgerdetail.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class FetchLedgerTransactionDetailUseCase @Inject constructor(
    private val ledgerDetailRepository: LedgerDetailRepository
): BaseUseCase<Int, Result<LedgerTransactionDetailEntity>>() {
    override suspend fun invoke(data: Int): Result<LedgerTransactionDetailEntity> =
        ledgerDetailRepository.fetchLedgerTransactionDetail(data)
}
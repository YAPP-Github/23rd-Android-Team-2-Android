package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionDetailEntity
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerTransactionDetailParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class UpdateLedgerTransactionDetailUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
): BaseUseCase<LedgerTransactionDetailParam, Result<LedgerTransactionDetailEntity>>() {
    override suspend fun invoke(data: LedgerTransactionDetailParam): Result<LedgerTransactionDetailEntity> =
        ledgerRepository.updateLedgerTransactionDetail(data)
}
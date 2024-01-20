package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class PostLedgerReceiptTransactionUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
): BaseUseCase<LedgerReceiptParam, Result<Unit>>() {
    override suspend fun invoke(data: LedgerReceiptParam): Result<Unit> =
        ledgerRepository.postLedgerReceiptTransaction(data)
}
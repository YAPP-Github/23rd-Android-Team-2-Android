package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerReceiptParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class PostLedgerReceiptTransactionUseCase @Inject constructor(
    private val ledgerDetailRepository: LedgerDetailRepository
): BaseUseCase<LedgerReceiptParam, Result<Unit>>() {
    override suspend fun invoke(data: LedgerReceiptParam): Result<Unit> =
        ledgerDetailRepository.postLedgerReceiptTransaction(data)
}
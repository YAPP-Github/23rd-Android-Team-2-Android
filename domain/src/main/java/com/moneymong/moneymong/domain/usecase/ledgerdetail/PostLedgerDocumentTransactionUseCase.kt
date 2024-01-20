package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class PostLedgerDocumentTransactionUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
): BaseUseCase<LedgerDocumentParam, Result<Unit>>() {
    override suspend fun invoke(data: LedgerDocumentParam): Result<Unit> =
        ledgerRepository.postLedgerDocumentTransaction(data)
}
package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.LedgerDocumentParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class PostLedgerDocumentTransactionUseCase @Inject constructor(
    private val ledgerDetailRepository: LedgerDetailRepository
): BaseUseCase<LedgerDocumentParam, Result<Unit>>() {
    override suspend fun invoke(data: LedgerDocumentParam): Result<Unit> =
        ledgerDetailRepository.postLedgerDocumentTransaction(data)
}
package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class DeleteLedgerDocumentTransactionUseCase @Inject constructor(
    private val ledgerDetailRepository: LedgerDetailRepository
) : BaseUseCase<DeleteLedgerDocumentParam, Result<Unit>>() {
    override suspend fun invoke(data: DeleteLedgerDocumentParam): Result<Unit> =
        ledgerDetailRepository.deleteLedgerDocumentTransaction(data)
}

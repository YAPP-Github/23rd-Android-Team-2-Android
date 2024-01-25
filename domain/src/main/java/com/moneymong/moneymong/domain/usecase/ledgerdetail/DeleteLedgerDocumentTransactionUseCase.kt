package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerDocumentParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class DeleteLedgerDocumentTransactionUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
) : BaseUseCase<DeleteLedgerDocumentParam, Result<Unit>>() {
    override suspend fun invoke(data: DeleteLedgerDocumentParam): Result<Unit> =
        ledgerRepository.deleteLedgerDocumentTransaction(data)
}

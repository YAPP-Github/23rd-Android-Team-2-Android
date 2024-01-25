package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class DeleteLedgerReceiptTransactionUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
): BaseUseCase<DeleteLedgerReceiptParam, Result<Unit>>() {
    override suspend fun invoke(data: DeleteLedgerReceiptParam): Result<Unit> =
        ledgerRepository.deleteLedgerReceiptTransaction(data)
}
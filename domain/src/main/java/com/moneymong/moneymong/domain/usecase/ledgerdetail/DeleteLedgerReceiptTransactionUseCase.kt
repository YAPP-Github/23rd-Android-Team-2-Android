package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.param.ledgerdetail.DeleteLedgerReceiptParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class DeleteLedgerReceiptTransactionUseCase @Inject constructor(
    private val ledgerDetailRepository: LedgerDetailRepository
): BaseUseCase<DeleteLedgerReceiptParam, Result<Unit>>() {
    override suspend fun invoke(data: DeleteLedgerReceiptParam): Result<Unit> =
        ledgerDetailRepository.deleteLedgerReceiptTransaction(data)
}
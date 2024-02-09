package com.moneymong.moneymong.domain.usecase.ledgerdetail

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.ledgerdetail.LedgerDetailRepository
import javax.inject.Inject

class DeleteLedgerDetailUseCase @Inject constructor(
    private val ledgerDetailRepository: LedgerDetailRepository
): BaseUseCase<Int, Result<Unit>>() {
    override suspend fun invoke(data: Int): Result<Unit> =
        ledgerDetailRepository.deleteLedgerDetail(data)
}
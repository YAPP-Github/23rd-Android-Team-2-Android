package com.moneymong.moneymong.domain.usecase.ledger

import com.moneymong.moneymong.domain.base.BaseUseCase
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class FetchAgencyExistLedgerUseCase @Inject constructor(
    private val ledgerRepository: LedgerRepository
) : BaseUseCase<Int, Result<Boolean>>() {
    override suspend fun invoke(data: Int): Result<Boolean> =
        ledgerRepository.fetchAgencyExistLedger(data)
}
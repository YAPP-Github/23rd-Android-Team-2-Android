package com.moneymong.moneymong.data.repository.ledger

import com.moneymong.moneymong.data.datasource.ledger.LedgerRemoteDataSource
import com.moneymong.moneymong.data.mapper.ledger.toEntity
import com.moneymong.moneymong.data.mapper.ledger.toRequest
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionEntity
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionListParam
import com.moneymong.moneymong.domain.param.ledger.LedgerTransactionParam
import com.moneymong.moneymong.domain.repository.ledger.LedgerRepository
import javax.inject.Inject

class LedgerRepositoryImpl @Inject constructor(
    private val ledgerRemoteDataSource: LedgerRemoteDataSource
) : LedgerRepository {
    override suspend fun fetchLedgerTransactionList(param: LedgerTransactionListParam): Result<LedgerTransactionListEntity> =
        ledgerRemoteDataSource.fetchLedgerTransactionList(
            id = param.id,
            year = param.year,
            month = param.month,
            page = param.page,
            limit = param.limit
        ).map { it.toEntity() }

    override suspend fun fetchAgencyExistLedger(agencyId: Int): Result<Boolean> =
        ledgerRemoteDataSource.fetchAgencyExistLedger(agencyId = agencyId)

    override suspend fun postLedgerTransaction(param: LedgerTransactionParam): Result<LedgerTransactionEntity> =
        ledgerRemoteDataSource.postLedgerTransaction(id = param.id, body = param.toRequest())
            .map { it.toEntity() }
}
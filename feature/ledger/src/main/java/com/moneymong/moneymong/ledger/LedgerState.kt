package com.moneymong.moneymong.ledger

import com.moneymong.moneymong.common.base.State
import com.moneymong.moneymong.domain.entity.ledger.LedgerTransactionListEntity

data class LedgerState(
    val isLoading: Boolean = false,
    val ledgerTransaction: LedgerTransactionListEntity? = null
): State

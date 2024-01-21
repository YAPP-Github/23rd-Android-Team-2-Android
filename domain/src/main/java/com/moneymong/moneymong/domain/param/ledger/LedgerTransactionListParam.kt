package com.moneymong.moneymong.domain.param.ledger

data class LedgerTransactionListParam(
    val id: Int,
    val year: Int,
    val month: Int,
    val page: Int,
    val limit: Int
)

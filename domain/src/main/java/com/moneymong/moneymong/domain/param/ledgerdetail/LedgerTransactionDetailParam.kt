package com.moneymong.moneymong.domain.param.ledgerdetail

data class LedgerTransactionDetailParam(
    val detailId: Int,
    val storeInfo: String,
    val amount: Int,
    val description: String,
    val paymentDate: String
)

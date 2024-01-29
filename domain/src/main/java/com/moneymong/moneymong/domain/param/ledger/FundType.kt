package com.moneymong.moneymong.domain.param.ledger

enum class FundType(val sign: String) {
    NONE(""),
    INCOME("+"),
    EXPENSE("-")
}
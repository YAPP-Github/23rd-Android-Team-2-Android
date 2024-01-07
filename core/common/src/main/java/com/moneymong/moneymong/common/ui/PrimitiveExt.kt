package com.moneymong.moneymong.common.ui

import java.text.DecimalFormat

fun String.toWonFormat(unit: Boolean = false): String {
    return if (this.isEmpty()) {
        this
    } else {
        if (unit) {
            "${DecimalFormat("#,###").format(this.toLong())}원"
        } else {
            DecimalFormat("#,###").format(this.toLong())
        }
    }
}
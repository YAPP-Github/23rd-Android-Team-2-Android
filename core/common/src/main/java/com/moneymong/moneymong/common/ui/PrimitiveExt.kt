package com.moneymong.moneymong.common.ui

import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat

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

fun String.isValidPaymentDate(): Boolean {
    val formatted = SimpleDateFormat("yyyyMMdd")
    formatted.isLenient = false
    try {
        formatted.parse(this)
    } catch (e: ParseException) {
        return false
    }
    return true
}

fun String.isValidPaymentTime(): Boolean {
    val formatted = SimpleDateFormat("HHmmss")
    formatted.isLenient = false
    try {
        formatted.parse(this)
    } catch (e: ParseException) {
        return false
    }
    return true
}
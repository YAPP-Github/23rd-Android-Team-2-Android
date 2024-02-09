package com.moneymong.moneymong.common.ui

import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.toWonFormat(unit: Boolean = false): String {
    return if (this.isEmpty()) {
        this
    } else {
        try {
            if (unit) {
                "${DecimalFormat("#,###").format(this.toLong())}원"
            } else {
                DecimalFormat("#,###").format(this.toLong())
            }
        } catch (e: Exception) {
            ""
        }
    }
}

fun String.validateValue(length: Int, isDigit: Boolean = false) =
    if (isDigit) {
        this.isDigitsOnly() && this.length <= length
    } else {
        this.length <= length
    }

fun String.isValidPaymentDate(): Boolean {
    val formatted = SimpleDateFormat("yyyyMMdd")
    formatted.isLenient = false
    try {
        formatted.parse(this)
    } catch (e: ParseException) {
        return false
    }

    try {
        val parsedDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyyMMdd"))
        if (parsedDate.isAfter(LocalDate.now())) {
            return false
        }
    } catch (e: DateTimeParseException) {
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
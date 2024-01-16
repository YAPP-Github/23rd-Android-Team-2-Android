package com.moneymong.moneymong.common.ext

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun String.toDateFormat(format: String): String {
    val outputFormatter = DateTimeFormatter.ofPattern(format)
    val instant = Instant.parse(this)

    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    return outputFormatter.format(localDateTime)
}
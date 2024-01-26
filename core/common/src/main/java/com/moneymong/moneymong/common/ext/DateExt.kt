package com.moneymong.moneymong.common.ext

import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toDateFormat(format: String): String {
    val outputFormatter = DateTimeFormatter.ofPattern(format)
    val instant = Instant.parse(this)

    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    return outputFormatter.format(localDateTime)
}

fun String.toZonedDateTime(format: String = "yyyy년 MM월 dd일 HH:mm:ss"): String {
    val formatter = DateTimeFormatter.ofPattern(format, Locale.KOREAN)
    val inputDateTime = LocalDateTime.parse(this, formatter)
    val offsetDateTime = OffsetDateTime.of(inputDateTime, ZoneOffset.ofHours(9))

    return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
}
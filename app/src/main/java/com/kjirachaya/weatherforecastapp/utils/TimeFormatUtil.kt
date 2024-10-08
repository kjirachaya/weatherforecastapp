package com.kjirachaya.weatherforecastapp.utils

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun Long.formatTimestampToDateTimeString(timezoneOffset: Int): String = formatTimestampToString(this, "d MMM yyyy, HH:mm", timezoneOffset)

fun Long.formatTimestampToTimeString(timezoneOffset: Int): String = formatTimestampToString(this, "HH:mm", timezoneOffset)

private fun formatTimestampToString(
    unixTimestamp: Long,
    pattern: String,
    timezoneOffset: Int,
): String {
    val instant = Instant.ofEpochSecond(unixTimestamp)
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val zoneOffset = ZoneOffset.ofTotalSeconds(timezoneOffset)
    return instant
        .atOffset(zoneOffset)
        .format(formatter)
}

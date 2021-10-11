package com.ricardo.muzmatchexercise.util

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Returns a string with either '{Day of week}' or
 * '{Day - month}' based on whether the Long timestamp is older than a week.
 */
fun Long.getMessageDay(): String {
    val lastWeek = LocalDateTime.now().minusWeeks(1).toInstant(ZoneOffset.UTC).epochSecond

    return if (this > lastWeek) {
        val dateFormat = DateTimeFormatter.ofPattern("EEEE");
        LocalDateTime.ofEpochSecond(this,0, ZoneOffset.UTC).format(dateFormat)
    } else {
        val dateFormat = DateTimeFormatter.ofPattern("dd MMMM")
        LocalDateTime.ofEpochSecond(this,0, ZoneOffset.UTC).format(dateFormat)
    }
}

/**
 * Returns a string with {Hour:minutes} based on the used Long timestamp.
 */
fun Long.getMessageTime(): String {
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    return LocalDateTime.ofEpochSecond(this,0, ZoneOffset.UTC).format(timeFormat)
}
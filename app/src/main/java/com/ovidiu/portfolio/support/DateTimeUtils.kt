package com.ovidiu.portfolio.support

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

object DateTimeUtils {
    fun millsToDate(millis: Long): String {
        val instant: Instant = Instant.ofEpochMilli(millis)

        val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

        return "${localDateTime.dayOfMonth}/${localDateTime.monthValue}/${localDateTime.year}"
    }
}
package com.ovidiu.portfolio.support

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

object DateTimeUtils {
    fun millsToDate(milli: Long): String {
        val instant: Instant = Instant.ofEpochMilli(milli)

        val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

        return "${localDateTime.dayOfMonth}/${localDateTime.monthValue}/${localDateTime.year}"
    }

    fun getInstantNow(): Instant {
        return Instant.now()
    }
}
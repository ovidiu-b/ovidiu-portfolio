package com.ovidiu.portfolio.support

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

object DateTimeUtils {
    fun millsToDate(milli: Long): String {
        val instant: Instant = Instant.ofEpochMilli(milli)

        val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

        val lessThanTen = localDateTime.monthValue < 10

        return "${if(lessThanTen) "0" else ""}${localDateTime.monthValue}/${localDateTime.year}"
    }

    fun getInstantNow(): Instant {
        return Instant.now()
    }
}
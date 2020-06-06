package com.ovidiu.portfolio.support

import android.content.Context
import androidx.core.content.edit

const val LAST_DATETIME_SYNC = "lastDateTimeSync"

class AppSettings(context: Context) {
    private val prefs = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

    fun setLastDateTimeSync(unix: Long) {
        prefs.edit { putLong(LAST_DATETIME_SYNC, unix) }
    }

    fun getLastDateTimeSync(): Long {
        return prefs.getLong(LAST_DATETIME_SYNC, 0L)
    }
}
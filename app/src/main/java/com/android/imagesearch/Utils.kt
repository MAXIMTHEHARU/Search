package com.android.imagesearch

import android.content.Context
import android.util.Log
import com.android.imagesearch.Constants.PREFS_NAME
import com.android.imagesearch.Constants.PREF_KEY
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object Utils {
    fun getDateFromTimestampWithFormat(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?
    ): String {
        var date: Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val df = SimpleDateFormat(toFormatformat)
        res = df.format(date)
        return res
    }

    fun saveLastSearch(context: Context, query: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(PREF_KEY, query).apply()
    }

    fun getLastSearch(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(PREF_KEY, null)
    }
}

package com.weather.app.data.date_formatters

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateFormatterImpl @Inject constructor() : DateFormatter {

    override fun getDateFromLong(value: Long, formatType: String, locale: String): String {
        return SimpleDateFormat(
            formatType,
            Locale(locale)
        ).format(value)
    }
}

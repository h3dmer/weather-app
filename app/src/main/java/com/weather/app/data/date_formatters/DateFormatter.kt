package com.weather.app.data.date_formatters

interface DateFormatter {

    fun getDateFromLong(value: Long, formatType: String, locale: String = "pl"): String
}

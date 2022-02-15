package com.weather.app.domain.models

data class WeatherDetails(
    val shortDate: String,
    val sunRise: String,
    val sunSet: String,
    val moonRise: String,
    val moonSet: String,
    val feelTemperatureMinimum: Pair<String, Int>,
    val feelTemperatureMaximum: Pair<String, Int>,
    val hoursOfSun: String,
    val dayDetails: DayDetails,
    val nightDetails: DayDetails
)

data class DayDetails(
    val icon: Int,
    val shortPhrase: String
)

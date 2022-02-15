package com.weather.app.data.remote.model.weather_details

import com.google.gson.annotations.SerializedName

data class DailyForecastsResponse(
    @SerializedName("EpochDate")
    val epochDate: Long,
    @SerializedName("Sun")
    val sun: DayLightResponse,
    @SerializedName("Moon")
    val moon: DayLightResponse,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: TemperatureResponse,
    @SerializedName("HoursOfSun")
    val hoursOfSun: Double,
    @SerializedName("Day")
    val day: DayDetailsResponse,
    @SerializedName("Night")
    val night: DayDetailsResponse,
)

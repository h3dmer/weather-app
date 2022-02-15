package com.weather.app.data.remote.model.weather_details

import com.google.gson.annotations.SerializedName

data class OneDayWeatherDetailsResponse(
    @SerializedName("DailyForecasts")
    val dailyForecastsResponse: List<DailyForecastsResponse>
)

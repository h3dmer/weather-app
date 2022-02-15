package com.weather.app.data.remote.model.weather_details

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    @SerializedName("Minimum")
    val minimum: TemperatureDetailsResponse,
    @SerializedName("Maximum")
    val maximum: TemperatureDetailsResponse,
)

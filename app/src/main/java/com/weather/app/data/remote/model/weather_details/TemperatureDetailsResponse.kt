package com.weather.app.data.remote.model.weather_details

import com.google.gson.annotations.SerializedName

data class TemperatureDetailsResponse(
    @SerializedName("Value")
    val value: String,
    @SerializedName("Unit")
    val unit: String,
)

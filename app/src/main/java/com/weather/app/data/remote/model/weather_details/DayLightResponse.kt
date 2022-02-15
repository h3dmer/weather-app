package com.weather.app.data.remote.model.weather_details

import com.google.gson.annotations.SerializedName

data class DayLightResponse(
    @SerializedName("EpochRise")
    val epochRise: Long,
    @SerializedName("EpochSet")
    val epochSet: Long
)

package com.weather.app.data.remote.model.weather_details

import com.google.gson.annotations.SerializedName

data class DayDetailsResponse(
    @SerializedName("Icon")
    val icon: Int,
    @SerializedName("ShortPhrase")
    val shortPhrase: String
)

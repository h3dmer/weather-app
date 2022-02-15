package com.weather.app.presentation.weatherdetails

import com.weather.app.domain.error.ApiErrorEntity
import com.weather.app.domain.models.WeatherDetails

data class WeatherDetailsState(
    val data: WeatherDetails? = null,
    val isLoading: Boolean = false,
    val isErrorOccur: ApiErrorEntity? = null
)

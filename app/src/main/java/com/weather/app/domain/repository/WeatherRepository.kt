package com.weather.app.domain.repository

import com.weather.app.domain.models.AutocompleteCity
import com.weather.app.domain.models.WeatherDetails
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getAutocompleteCityName(cityInput: String): Single<List<AutocompleteCity>>

    fun getOneDayWeatherDetails(id: String): Single<WeatherDetails>
}

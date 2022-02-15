package com.weather.app.data.remote.repository

import com.weather.app.data.mappers.AutocompleteCityResponseMapper
import com.weather.app.data.mappers.WeatherDetailsMapper
import com.weather.app.data.remote.service.WeatherService
import com.weather.app.domain.models.AutocompleteCity
import com.weather.app.domain.models.WeatherDetails
import com.weather.app.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val cityMapper: AutocompleteCityResponseMapper,
    private val toWeatherDetails: WeatherDetailsMapper
) : WeatherRepository {

    override fun getAutocompleteCityName(cityInput: String): Single<List<AutocompleteCity>> {
        return weatherService.getAutocompleteCityName(cityInput)
            .map { cityMapper.map(it) }
    }

    override fun getOneDayWeatherDetails(id: String): Single<WeatherDetails> {
        return weatherService.getOneDayWeatherDetails(id)
            .map { toWeatherDetails.map(it) }
    }
}

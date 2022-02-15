package com.weather.app.domain.usecase

import com.weather.app.domain.common.toResult
import com.weather.app.domain.error.ApiErrorHandler
import com.weather.app.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCityAutocompleteUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val errorHandler: ApiErrorHandler
) {

    operator fun invoke(cityName: String) =
        weatherRepository.getAutocompleteCityName(cityName).toResult(errorHandler)
}

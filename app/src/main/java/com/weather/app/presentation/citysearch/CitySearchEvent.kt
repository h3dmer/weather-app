package com.weather.app.presentation.citysearch

sealed class CitySearchEvent {

    data class NavigateToWeatherDetails(val id: String, val cityName: String) : CitySearchEvent()
}

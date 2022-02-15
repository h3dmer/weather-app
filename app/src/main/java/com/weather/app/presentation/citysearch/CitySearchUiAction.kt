package com.weather.app.presentation.citysearch

sealed class CitySearchUiAction {

    object OnScreenCreated : CitySearchUiAction()
    data class SearchInputChange(val searchInput: String) : CitySearchUiAction()
    data class OnCityClicked(val id: String, val cityName: String) : CitySearchUiAction()
}

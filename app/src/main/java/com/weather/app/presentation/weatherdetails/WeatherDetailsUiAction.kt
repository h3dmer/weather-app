package com.weather.app.presentation.weatherdetails

sealed class WeatherDetailsUiAction {

    data class OnDetailsCreate(val id: String) : WeatherDetailsUiAction()
}

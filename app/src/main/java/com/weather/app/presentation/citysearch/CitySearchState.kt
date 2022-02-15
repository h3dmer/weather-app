package com.weather.app.presentation.citysearch

import com.weather.app.domain.models.AutocompleteCity
import javax.annotation.concurrent.Immutable

@Immutable
data class CitySearchState(
    val data: List<AutocompleteCity>? = listOf(),
    val isLoading: Boolean = false
)

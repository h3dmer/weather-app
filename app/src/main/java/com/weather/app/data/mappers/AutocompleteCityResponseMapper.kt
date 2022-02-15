package com.weather.app.data.mappers

import com.weather.app.data.remote.model.autocomplete.AutocompleteCityResponse
import com.weather.app.domain.models.AutocompleteCity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AutocompleteCityResponseMapper @Inject constructor() :
    Mapper<List<AutocompleteCityResponse>, List<AutocompleteCity>> {

    override fun map(from: List<AutocompleteCityResponse>): List<AutocompleteCity> {
        return from.map { createAutocompleteCity(it) }
    }

    private fun createAutocompleteCity(from: AutocompleteCityResponse): AutocompleteCity {
        from.run {
            return AutocompleteCity(
                id = id,
                type = type,
                name = name,
                countryName = country.localizedName,
                areaName = area.localizedName
            )
        }
    }
}

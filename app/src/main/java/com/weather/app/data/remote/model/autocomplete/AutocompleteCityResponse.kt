package com.weather.app.data.remote.model.autocomplete

import com.google.gson.annotations.SerializedName

data class AutocompleteCityResponse(
    @SerializedName("Key")
    val id: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("LocalizedName")
    val name: String,
    @SerializedName("Country")
    val country: AutocompleteCountryResponse,
    @SerializedName("AdministrativeArea")
    val area: AutocompleteAreaResponse
)

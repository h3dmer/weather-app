package com.weather.app.data.remote.model.autocomplete

import com.google.gson.annotations.SerializedName

data class AutocompleteCountryResponse(
    @SerializedName("LocalizedName")
    val localizedName: String
)

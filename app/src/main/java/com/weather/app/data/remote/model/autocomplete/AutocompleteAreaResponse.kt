package com.weather.app.data.remote.model.autocomplete

import com.google.gson.annotations.SerializedName

data class AutocompleteAreaResponse(
    @SerializedName("LocalizedName")
    val localizedName: String
)

package com.weather.app.domain.models

data class AutocompleteCity(
    val id: String,
    val type: String,
    val name: String,
    val countryName: String,
    val areaName: String
) : UniqueId {
    override fun getUniqueId(): Any = id
}

interface UniqueId {
    fun getUniqueId(): Any?
}

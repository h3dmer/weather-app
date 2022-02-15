package com.weather.app.domain.validator

import javax.inject.Inject
import javax.inject.Named

const val CITY_NAME_VALIDATOR = "city_name"

@Named(CITY_NAME_VALIDATOR)
class CityNameValidator @Inject constructor() : Validator {

    override fun validate(value: String): Boolean {
        return value.matches("([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*\$".toRegex()) && value.isNotEmpty()
    }
}

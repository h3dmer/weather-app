package com.weather.app.domain.validator

interface Validator {

    fun validate(value: String): Boolean
}

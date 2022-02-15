package com.weather.app.domain.error

interface ApiErrorHandler {

    fun getError(throwable: Throwable): ApiErrorEntity
}

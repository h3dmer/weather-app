package com.weather.app.domain.error

sealed class ApiErrorEntity {
    object Network : ApiErrorEntity()
    object NotFound : ApiErrorEntity()
    object AccessDenied : ApiErrorEntity()
    object ServiceUnavailable : ApiErrorEntity()
    object Unknown : ApiErrorEntity()
}

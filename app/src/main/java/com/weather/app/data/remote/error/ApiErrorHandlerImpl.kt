package com.weather.app.data.remote.error

import com.weather.app.domain.error.ApiErrorEntity
import com.weather.app.domain.error.ApiErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAVAILABLE
import javax.inject.Inject

class ApiErrorHandlerImpl @Inject constructor() : ApiErrorHandler {

    override fun getError(throwable: Throwable): ApiErrorEntity {
        return when (throwable) {
            is IOException -> ApiErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    HTTP_NOT_FOUND -> ApiErrorEntity.NotFound
                    HTTP_FORBIDDEN -> ApiErrorEntity.AccessDenied
                    HTTP_UNAVAILABLE -> ApiErrorEntity.ServiceUnavailable
                    else -> ApiErrorEntity.Unknown
                }
            }
            else -> ApiErrorEntity.Unknown
        }
    }
}

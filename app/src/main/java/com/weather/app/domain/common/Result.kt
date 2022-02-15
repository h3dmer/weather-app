package com.weather.app.domain.common

import com.weather.app.domain.error.ApiErrorEntity
import com.weather.app.domain.error.ApiErrorHandler
import io.reactivex.rxjava3.core.Single
import java.lang.Exception

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ApiErrorEntity) : Result<T>()
}

fun <T> Single<T>.toResult(handler: ApiErrorHandler): Single<Result<T>> {
    return this.map {
        try {
            Result.Success(it)
        } catch (e: Exception) {
            Result.Error(ApiErrorEntity.Unknown)
        }
    }
        .onErrorReturn {
            Result.Error(handler.getError(it))
        }
}

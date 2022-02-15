package com.weather.app.data.remote.interceptor

import com.weather.app.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val API_KEY = "apikey"

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().url(getModifiedUrl(chain)).build().also {
            return chain.proceed(it)
        }
    }

    private fun getModifiedUrl(chain: Interceptor.Chain): HttpUrl {
        return chain.request().url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.apiKey)
            .build()
    }
}

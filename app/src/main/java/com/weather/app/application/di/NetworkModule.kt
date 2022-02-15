package com.weather.app.application.di

import com.google.gson.GsonBuilder
import com.weather.app.BuildConfig
import com.weather.app.data.remote.interceptor.AuthInterceptor
import com.weather.app.data.remote.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val WEB_URL = "http://dataservice.accuweather.com/"
private const val TIME_OUT_TIME = 30L

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(WEB_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: Set<@JvmSuppressWildcards Interceptor>,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                interceptors.forEach(::addInterceptor)
                connectTimeout(TIME_OUT_TIME, TimeUnit.SECONDS)
                readTimeout(TIME_OUT_TIME, TimeUnit.SECONDS)
                writeTimeout(TIME_OUT_TIME, TimeUnit.SECONDS)
            }
            .build()

    @Provides
    fun compositeDisposable() = CompositeDisposable()

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @IntoSet
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(Level.BODY)
            } else {
                setLevel(Level.NONE)
            }
        }
    }

    @IntoSet
    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor = AuthInterceptor()
}

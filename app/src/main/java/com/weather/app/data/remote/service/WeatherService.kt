package com.weather.app.data.remote.service

import com.weather.app.data.remote.model.autocomplete.AutocompleteCityResponse
import com.weather.app.data.remote.model.weather_details.OneDayWeatherDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("locations/v1/cities/autocomplete")
    fun getAutocompleteCityName(
        @Query("q") cityName: String
    ): Single<List<AutocompleteCityResponse>>

    @GET("forecasts/v1/daily/1day/{id}")
    fun getOneDayWeatherDetails(
        @Path("id") id: String,
        @Query("language") language: String = "pl",
        @Query("details") details: Boolean = true,
        @Query("metric") metric: Boolean = true,
    ): Single<OneDayWeatherDetailsResponse>
}

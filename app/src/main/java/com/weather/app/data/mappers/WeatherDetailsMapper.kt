package com.weather.app.data.mappers

import com.weather.app.R
import com.weather.app.data.date_formatters.DateFormatter
import com.weather.app.data.remote.model.weather_details.DayDetailsResponse
import com.weather.app.data.remote.model.weather_details.OneDayWeatherDetailsResponse
import com.weather.app.domain.models.DayDetails
import com.weather.app.domain.models.WeatherDetails
import javax.inject.Inject
import javax.inject.Singleton

private const val DATE_FORMAT_DD_MM = "dd.MM"
private const val DATE_FORMAT_HOUR_MINUTES = "HH:mm"
private const val TO_LONG_VALUE = 1000L

@Singleton
class WeatherDetailsMapper @Inject constructor(
    private val dateFormatter: DateFormatter
) : Mapper<OneDayWeatherDetailsResponse, WeatherDetails> {

    override fun map(from: OneDayWeatherDetailsResponse): WeatherDetails {
        with(from.dailyForecastsResponse.first()) {
            with(dateFormatter) {
                return WeatherDetails(
                    shortDate = getDateFromLong(epochDate, DATE_FORMAT_DD_MM),
                    sunRise = getDateFromLong(
                        sun.epochRise * TO_LONG_VALUE,
                        DATE_FORMAT_HOUR_MINUTES
                    ),
                    sunSet = getDateFromLong(
                        sun.epochSet * TO_LONG_VALUE,
                        DATE_FORMAT_HOUR_MINUTES
                    ),
                    moonRise = getDateFromLong(
                        moon.epochRise * TO_LONG_VALUE,
                        DATE_FORMAT_HOUR_MINUTES
                    ),
                    moonSet = getDateFromLong(
                        moon.epochSet * TO_LONG_VALUE,
                        DATE_FORMAT_HOUR_MINUTES
                    ),
                    feelTemperatureMinimum = Pair(
                        "${realFeelTemperature.minimum.value}°${realFeelTemperature.minimum.unit}",
                        getTemperatureSecondValue(realFeelTemperature.minimum.value)
                    ),
                    feelTemperatureMaximum = Pair(
                        "${realFeelTemperature.maximum.value}°${realFeelTemperature.maximum.unit}",
                        getTemperatureSecondValue(realFeelTemperature.maximum.value)
                    ),
                    hoursOfSun = "$hoursOfSun h",
                    dayDetails = mapDayDetails(day),
                    nightDetails = mapDayDetails(night)
                )
            }
        }
    }

    private fun getTemperatureSecondValue(temp: String): Int {
        return temp.toDouble().let { temperature ->
            when {
                temperature > 20 -> {
                    R.color.red
                }
                temperature in 20.0..10.0 -> {
                    R.color.black
                }
                else -> {
                    R.color.blue
                }
            }
        }
    }

    private fun mapDayDetails(response: DayDetailsResponse): DayDetails {
        return DayDetails(
            response.icon,
            response.shortPhrase
        )
    }
}

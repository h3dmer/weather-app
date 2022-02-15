package com.weather.app.application.di

import com.weather.app.data.date_formatters.DateFormatter
import com.weather.app.data.date_formatters.DateFormatterImpl
import com.weather.app.data.remote.error.ApiErrorHandlerImpl
import com.weather.app.data.remote.repository.WeatherRepositoryImpl
import com.weather.app.domain.error.ApiErrorHandler
import com.weather.app.domain.repository.WeatherRepository
import com.weather.app.domain.validator.CITY_NAME_VALIDATOR
import com.weather.app.domain.validator.CityNameValidator
import com.weather.app.domain.validator.Validator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    //region Repository binds

    @Binds
    abstract fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository

    //endregion

    //region Helper binds

    @Binds
    abstract fun bindDateFormatter(dateFormatter: DateFormatterImpl): DateFormatter

    //endregion

    //region Error handlers binds

    @Binds
    abstract fun bindApiErrorHandler(handler: ApiErrorHandlerImpl): ApiErrorHandler

    //endregion

    //region Validators binds

    @Binds
    @Named(CITY_NAME_VALIDATOR)
    abstract fun bindCityNameValidator(validator: CityNameValidator): Validator

    //endregion
}

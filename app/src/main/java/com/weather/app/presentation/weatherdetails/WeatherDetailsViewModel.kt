package com.weather.app.presentation.weatherdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.app.core.extensions.result
import com.weather.app.domain.common.Result
import com.weather.app.domain.usecase.GetOneDayWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherDetailsUseCase: GetOneDayWeatherDetailsUseCase,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val _weatherDetailsState = MutableLiveData(WeatherDetailsState())
    val weatherDetailsState: LiveData<WeatherDetailsState>
        get() = _weatherDetailsState

    fun onAction(action: WeatherDetailsUiAction) {
        when (action) {
            is WeatherDetailsUiAction.OnDetailsCreate -> {
                getWeatherDetails(action.id)
            }
        }
    }

    private fun getWeatherDetails(id: String) {
        _weatherDetailsState.value?.let { state ->
            weatherDetailsUseCase.invoke(id)
                .doOnSubscribe { _weatherDetailsState.postValue(state.copy(isLoading = true)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .result(compositeDisposable) {
                    when (it) {
                        is Result.Success -> {
                            _weatherDetailsState.postValue(
                                state.copy(
                                    data = it.data,
                                    isLoading = false
                                )
                            )
                        }
                        is Result.Error -> {
                            _weatherDetailsState.postValue(
                                state.copy(
                                    isLoading = false,
                                    isErrorOccur = it.error
                                )
                            )
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

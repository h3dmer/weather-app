package com.weather.app.presentation.citysearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.weather.app.core.extensions.result
import com.weather.app.domain.common.Result
import com.weather.app.domain.usecase.GetCityAutocompleteUseCase
import com.weather.app.domain.validator.CITY_NAME_VALIDATOR
import com.weather.app.domain.validator.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

private const val SEARCH_INPUT_DELAY = 700L

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val getCityAutocompleteUseCase: GetCityAutocompleteUseCase,
    @Named(CITY_NAME_VALIDATOR) private val cityNameValidator: Validator,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val userTypeSubject = BehaviorSubject.create<String>()

    private val _state = MutableLiveData(CitySearchState())
    val state: LiveData<CitySearchState>
        get() = _state

    private val _event = LiveEvent<CitySearchEvent>()
    val event: LiveData<CitySearchEvent>
        get() = _event

    fun onAction(action: CitySearchUiAction) {
        return when (action) {
            is CitySearchUiAction.OnScreenCreated -> {
                onScreenCreated()
            }
            is CitySearchUiAction.SearchInputChange -> {
                userTypeSubject.onNext(action.searchInput)
            }
            is CitySearchUiAction.OnCityClicked -> {
                _event.value = CitySearchEvent.NavigateToWeatherDetails(action.id, action.cityName)
            }
        }
    }

    private fun getCityAutocomplete(cityName: String) {
        _state.value?.let { uiState ->
            getCityAutocompleteUseCase(cityName)
                .doOnSubscribe { _state.postValue(uiState.copy(isLoading = true)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .result(compositeDisposable) { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.value = uiState.copy(data = result.data, isLoading = false)
                        }
                        is Result.Error -> {
                            _state.value = uiState.copy(data = listOf(), isLoading = false)
                            Timber.d(result.error.toString())
                        }
                    }
                }
        }
    }

    private fun onScreenCreated() {
        userTypeSubject
            .debounce(SEARCH_INPUT_DELAY, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .map { inputText ->
                if (cityNameValidator.validate(inputText)) {
                    getCityAutocomplete(inputText)
                } else {
                    _state.postValue(state.value?.copy(data = listOf(), isLoading = false))
                }
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

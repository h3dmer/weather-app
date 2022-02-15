package com.weather.app.presentation.citysearch

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.weather.app.R
import com.weather.app.core.viewBinding
import com.weather.app.databinding.FragmentCitySearchBinding
import com.weather.app.presentation.citysearch.CitySearchUiAction.OnCityClicked
import com.weather.app.presentation.citysearch.adapter.CitySearchAdapter
import com.weather.app.presentation.utils.DividerItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitySearchFragment : Fragment(R.layout.fragment_city_search) {

    private val binding: FragmentCitySearchBinding by viewBinding(FragmentCitySearchBinding::bind)

    private val citySearchViewModel: CitySearchViewModel by viewModels()

    private val citySearchAdapter by lazy {
        CitySearchAdapter { weatherId, cityName ->
            citySearchViewModel.onAction(OnCityClicked(weatherId, cityName))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeViewModel()
    }

    private fun setupView() {
        binding.run {
            citySearchViewModel.onAction(CitySearchUiAction.OnScreenCreated)
            searchInputEditText.doOnTextChanged { text, _, _, _ ->
                citySearchViewModel.onAction(CitySearchUiAction.SearchInputChange(text.toString()))
            }
            citiesList.adapter = citySearchAdapter
            citiesList.addItemDecoration(DividerItemDecorator(requireContext()))
        }
    }

    private fun observeViewModel() {
        with(citySearchViewModel) {
            state.observe(viewLifecycleOwner, ::handleState)
            event.observe(viewLifecycleOwner, ::handleEvent)
        }
    }

    private fun handleState(state: CitySearchState) {
        with(binding) {
            citySearchAdapter.submitList(state.data)
            noDataMessage.isVisible = state.data.isNullOrEmpty()
            citiesList.isVisible = !state.data.isNullOrEmpty()
            loadingBar.isVisible = state.isLoading
        }
    }

    private fun handleEvent(event: CitySearchEvent) {
        when (event) {
            is CitySearchEvent.NavigateToWeatherDetails -> navigateToWeatherDetails(
                event.id,
                event.cityName
            )
        }
    }

    private fun navigateToWeatherDetails(id: String, cityName: String) {
        findNavController().navigate(
            CitySearchFragmentDirections.actionCitySearchFragmentToWeatherDetailsFragment(
                id,
                cityName
            )
        )
    }
}

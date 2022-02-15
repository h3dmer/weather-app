package com.weather.app.presentation.weatherdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.weather.app.R
import com.weather.app.core.extensions.getColor
import com.weather.app.core.viewBinding
import com.weather.app.databinding.FragmentWeatherDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment(R.layout.fragment_weather_details) {

    private val binding by viewBinding(FragmentWeatherDetailsBinding::bind)

    private val weatherDetailsViewModel by viewModels<WeatherDetailsViewModel>()

    private val args by navArgs<WeatherDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDetailsViewModel.onAction(WeatherDetailsUiAction.OnDetailsCreate(args.id))
        setupView()
    }

    private fun setupView() {
        with(binding) {
            weatherDetailsBackButton.setOnClickListener { findNavController().navigateUp() }
            weatherDetailsViewModel.weatherDetailsState.observe(viewLifecycleOwner, ::handleState)
        }
    }

    private fun handleState(state: WeatherDetailsState) {
        with(binding) {
            cityName.text = args.cityName
            dayDetails.apply {
                dayTimeTitle.text = getString(R.string.weather_details_day)
                weatherShortDescription.text = state.data?.dayDetails?.shortPhrase
                temperatureText.text = state.data?.feelTemperatureMaximum?.first
                temperatureText.setTextColor(getColor(state.data?.feelTemperatureMaximum?.second))
                sunRiseText.text = getString(R.string.weather_details_sun_rise, state.data?.sunRise)
                sunSetText.text = getString(R.string.weather_details_sun_set, state.data?.sunSet)
            }
            nightDetails.apply {
                dayTimeTitle.text = getString(R.string.weather_details_night)
                weatherShortDescription.text = state.data?.nightDetails?.shortPhrase
                temperatureText.text = state.data?.feelTemperatureMinimum?.first
                temperatureText.setTextColor(getColor(state.data?.feelTemperatureMinimum?.second))
                sunRiseText.text =
                    getString(R.string.weather_details_moon_rise, state.data?.moonRise)
                sunSetText.text = getString(R.string.weather_details_moon_set, state.data?.moonSet)
            }
        }
    }
}

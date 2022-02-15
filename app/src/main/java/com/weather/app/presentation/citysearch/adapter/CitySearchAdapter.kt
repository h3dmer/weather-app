package com.weather.app.presentation.citysearch.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.weather.app.core.adapter.GenericDiffCallback
import com.weather.app.databinding.ItemCitySearchBinding
import com.weather.app.domain.models.AutocompleteCity

class CitySearchAdapter(private val cityClicked: (String, String) -> Unit) :
    ListAdapter<AutocompleteCity, CitySearchViewHolder>(GenericDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchViewHolder {
        return CitySearchViewHolder(parent, ItemCitySearchBinding::inflate, cityClicked)
    }

    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

package com.weather.app.presentation.citysearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.weather.app.R
import com.weather.app.core.BaseViewHolder
import com.weather.app.databinding.ItemCitySearchBinding
import com.weather.app.domain.models.AutocompleteCity

class CitySearchViewHolder(
    parent: ViewGroup,
    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> ItemCitySearchBinding,
    private val cityClicked: (String, String) -> Unit
) : BaseViewHolder<ItemCitySearchBinding, AutocompleteCity>(parent, creator) {

    override fun bind(item: AutocompleteCity) {
        with(binding) {
            with(item) {
                cityTitle.text = name
                cityDescription.text = itemView.context.getString(
                    R.string.search_city_localized_description_name,
                    areaName,
                    countryName
                )
                root.setOnClickListener {
                    cityClicked(id, name)
                }
            }
        }
    }
}

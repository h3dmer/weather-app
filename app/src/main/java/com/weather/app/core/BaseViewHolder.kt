package com.weather.app.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : ViewBinding, I> private constructor(
    val binding: T
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parent: ViewGroup,
        creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T
    ) : this(creator(LayoutInflater.from(parent.context), parent, false))

    abstract fun bind(item: I)

    protected fun getString(@StringRes stringRes: Int) = itemView.context.getString(stringRes)

    protected fun getColor(@ColorRes colorRes: Int) = itemView.context.getColor(colorRes)
}

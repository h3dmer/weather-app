package com.weather.app.core.extensions

import androidx.fragment.app.Fragment
import com.weather.app.R

fun Fragment.getColor(id: Int?): Int {
    return resources.getColor(
        id ?: R.color.black,
        null
    )
}

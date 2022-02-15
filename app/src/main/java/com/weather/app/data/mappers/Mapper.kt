package com.weather.app.data.mappers

fun interface Mapper<F, T> {
    fun map(from: F): T
}

package com.weather.app.core.extensions

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

fun <T : Any> Single<T>.result(
    disposable: CompositeDisposable,
    onSuccess: (T) -> Unit
) = subscribeBy(Timber::e) { result ->
    onSuccess.invoke(result)
}.addTo(disposable)

package com.jsouza.moviedetail.presentation.utils

import androidx.lifecycle.LiveData

private fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = CustomObserver(handler = onChangeHandler)
    observe(observer, observer)
}

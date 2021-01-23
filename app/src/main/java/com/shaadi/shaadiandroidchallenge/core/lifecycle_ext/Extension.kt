package com.shaadi.shaadiandroidchallenge.core.lifecycle_ext

import androidx.lifecycle.MutableLiveData

inline fun <T> MutableLiveData<T>.asyncNotifyLiveData(block: T.() -> Unit) {
    value?.let {
        block(it)
    }

    this.postValue(this.value)
}

inline fun <T> MutableLiveData<T>.notifyLiveData(block: T.() -> Unit) {
    value?.let {
        block(it)
    }

    value = value
}
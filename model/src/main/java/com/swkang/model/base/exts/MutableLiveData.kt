package com.swkang.model.base.exts

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Boolean>.setTrue() {
    value = true
}

fun MutableLiveData<Boolean>.setFalse() {
    value = false
}
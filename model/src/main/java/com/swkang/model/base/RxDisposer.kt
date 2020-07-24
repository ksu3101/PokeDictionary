package com.swkang.model.base

import io.reactivex.rxjava3.disposables.Disposable

interface RxDisposer {
    fun addDisposer(disposable: Disposable)

    fun dispose()
}
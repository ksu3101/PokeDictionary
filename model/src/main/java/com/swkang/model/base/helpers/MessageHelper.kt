package com.swkang.model.base.helpers

import androidx.annotation.StringRes
import io.reactivex.rxjava3.core.Completable

interface MessageHelper {

    fun showToast(@StringRes messageResId: Int, message: String? = null)

    fun createReTryActionDialog(
        title: CharSequence = "",
        message: CharSequence
    ): Completable

}
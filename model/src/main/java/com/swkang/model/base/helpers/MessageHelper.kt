package com.swkang.model.base.helpers

import androidx.annotation.StringRes

interface MessageHelper {

    fun showToast(@StringRes messageResId: Int, message: String? = null)

}
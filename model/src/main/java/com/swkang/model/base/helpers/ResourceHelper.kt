package com.swkang.model.base.helpers

import androidx.annotation.StringRes

interface ResourceHelper {

    fun getString(@StringRes stringResId: Int): String

    fun getString(@StringRes stringResId: Int, vararg params: Any): String

}
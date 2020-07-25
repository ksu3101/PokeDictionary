package com.swkang.pokedictionary.base.helpers

import android.content.Context
import com.swkang.model.base.helpers.ResourceHelper

class ResourceHelperImpl(
    private val context: Context
): ResourceHelper {

    override fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }

}
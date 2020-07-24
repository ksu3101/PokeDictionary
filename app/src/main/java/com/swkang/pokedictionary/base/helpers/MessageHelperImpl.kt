package com.swkang.pokedictionary.base.helpers

import android.content.Context
import android.widget.Toast
import com.swkang.model.base.helpers.MessageHelper

class MessageHelperImpl(
    private val context: Context
) : MessageHelper {

    override fun showToast(messageResId: Int, message: String?) {
        val msg = if (messageResId == 0 && message.isNullOrEmpty()) {
            throw IllegalArgumentException("message parameter has not available.")
        } else {
            if (!message.isNullOrEmpty()) message
            else context.getString(messageResId)
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}
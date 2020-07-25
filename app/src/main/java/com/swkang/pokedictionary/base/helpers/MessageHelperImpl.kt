package com.swkang.pokedictionary.base.helpers

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.swkang.model.base.helpers.MessageHelper
import com.swkang.pokedictionary.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

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

    override fun createReTryActionDialog(title: CharSequence, message: CharSequence): Completable =
        createDialogSource(
            title,
            message
        ).flatMapCompletable { Completable.complete() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun createDialogSource(
        title: CharSequence,
        message: CharSequence,
        @StringRes positiveBtnResId: Int = R.string.c_yes,
        @StringRes negativeBtnResId: Int = R.string.c_no,
        isCancelable: Boolean = true,
        isTwoBtn: Boolean = false
    ): Maybe<Boolean> =
        Maybe.create { emitter ->
            val builder = AlertDialog.Builder(context)
            if (title.isNotEmpty()) builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton(context.getString(positiveBtnResId)) { _, _ ->
                emitter.onSuccess(true)
                emitter.onComplete()
            }
            if (isTwoBtn) {
                builder.setNegativeButton(context.getString(negativeBtnResId)) { _, _ ->
                    emitter.onSuccess(false)
                    emitter.onComplete()
                }
            }
            if (isCancelable) {
                builder.setOnCancelListener {
                    if (isTwoBtn) emitter.onSuccess(false)
                    emitter.onComplete()
                }
            }
            val dialog = builder.create()
            emitter.setDisposable(Disposable.fromRunnable {
                dialog.dismiss()
            })
            dialog.show()
        }

}
package com.swkang.pokedictionary.base.widgets

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RxEditText : AppCompatEditText {
    private val textWatcherRef: TextWatcher
    private val textChanged = PublishSubject.create<String>()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        textWatcherRef = addTextChangedListener {
            textChanged.onNext(it?.toString() ?: "")
        }
    }

    fun getTextChangedListener(): Observable<String> {
        return textChanged.hide().observeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .debounce(500, TimeUnit.MILLISECONDS)
    }

    fun dispose() {
        textChanged.onComplete()
        removeTextChangedListener(textWatcherRef)
    }

    override fun onDetachedFromWindow() {
        dispose()
        super.onDetachedFromWindow()
    }

}
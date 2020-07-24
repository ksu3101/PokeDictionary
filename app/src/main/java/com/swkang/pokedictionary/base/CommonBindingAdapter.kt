package com.swkang.pokedictionary.base

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swkang.model.base.RxDisposer
import com.swkang.pokedictionary.base.widgets.RxEditText
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImageUrl(iv: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        iv.setImageDrawable(null)
    } else {
        Glide.with(iv.context)
            .load(url)
            .into(iv)
    }
}

@BindingAdapter(value = ["disposer", "textChanged"])
fun addTextChangedListener(
    et: RxEditText,
    disposer: RxDisposer,
    textChangedListener: (String) -> Unit
) {
    disposer.addDisposer(
        et.getTextChangedListener()
            .subscribe {
                textChangedListener(it)
            }
    )
}

@BindingAdapter("addItemDivider")
fun setDividerToRecyclerView(rv: RecyclerView, addItemDivider: Boolean) {
    if (!addItemDivider) return
    val layoutManager = rv.layoutManager
    if (layoutManager is LinearLayoutManager) {
        val dividerItemDecoration =
            DividerItemDecoration(rv.getContext(), layoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration)
    }
}
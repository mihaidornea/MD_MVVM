package com.example.wd_mvvm.utils

import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.example.wd_mvvm.models.article.Article
import com.example.wd_mvvm.ui.news.NewsAdapter

@BindingAdapter("otpText")
fun setOtp(view: PinEntryEditText, newValue: String) {
    if (!view.text?.equals(newValue)!!) {
        view.setText(newValue)
    }
}

@InverseBindingAdapter(attribute = "otpText")
fun getOtp(view: PinEntryEditText): String {
    return view.text.toString()
}

@BindingAdapter("otpTextAttrChanged")
fun setListener(
    view: PinEntryEditText,
    attrChange: InverseBindingListener
) {
    view.setOnPinEnteredListener { attrChange.onChange() }
}

@BindingAdapter("otpListener")
fun setOnPinEnteredListener(
    view: PinEntryEditText,
    callback: PinEntryEditText.OnPinEnteredListener
) {
    view.setOnPinEnteredListener(callback)
}

@BindingAdapter("setListContent")
fun bindRecyclerViewAdapter(view: RecyclerView, list: List<Article>?) {
    if (list == null) return
    (view.adapter as? NewsAdapter)?.let {
        it.news = list.toMutableList()
    }
}
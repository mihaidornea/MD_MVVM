package com.example.wd_mvvm.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.alimuzaffar.lib.pin.PinEntryEditText

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
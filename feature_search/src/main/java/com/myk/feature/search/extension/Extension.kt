package com.myk.feature.search.extension

import android.view.View
import android.widget.TextView

fun TextView.setTextOrHide(text: CharSequence?) {
    visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
    setText(text ?: "")
}

package com.ovidiu.portfolio.support

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTextWithLines")
fun setTextWithLines(textView: TextView, text: String) {
    textView.text = text.replace("nLine", System.getProperty("line.separator")!!)
}

@BindingAdapter("setTextWithoutLines")
fun setTextWithoutLines(textView: TextView, text: String) {
    textView.text = text.replace("nLine", "")
}
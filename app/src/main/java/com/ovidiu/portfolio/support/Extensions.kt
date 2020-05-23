package com.ovidiu.portfolio.support

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.RawRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ViewGroup.getLayoutBinding(@LayoutRes layoutResource: Int) : ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(this.context), layoutResource, this, false)
}

fun AppCompatImageView.circleDrawable(@RawRes @DrawableRes drawableResource: Int) {
    Glide
        .with(this.context)
        .load(drawableResource)
        .apply(RequestOptions().circleCrop())
        .into(this)
}
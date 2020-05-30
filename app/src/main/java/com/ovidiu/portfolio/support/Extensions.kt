package com.ovidiu.portfolio.support

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.RawRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ViewGroup.getLayoutBinding(@LayoutRes layoutResource: Int) : ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(this.context), layoutResource, this, false)
}

fun AppCompatImageView.circleDrawable(@DrawableRes drawableResource: Int) {
    Glide
        .with(this.context)
        .load(drawableResource)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun AppCompatImageView.srcDrawable(@DrawableRes drawableResource: Int) {
    Glide
        .with(this.context)
        .load(drawableResource)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(this)
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>
package com.ovidiu.portfolio.support

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
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

fun ImageView.asCircle(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>
package com.ovidiu.portfolio.support

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.getLayoutBinding(@LayoutRes layoutResource: Int) : ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(this.context), layoutResource, this, false)
}

fun ImageView.asCircle(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun View.setupSnackbar(lifecycleOwner: LifecycleOwner, snackbarEvent: LiveData<LiveDataEvent<Int>>, timeLength: Int) {

    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            showSnackbar(context.getString(it), timeLength)
        }
    })
}

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}
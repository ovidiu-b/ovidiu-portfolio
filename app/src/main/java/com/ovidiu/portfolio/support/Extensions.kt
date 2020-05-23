package com.ovidiu.portfolio.support

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.getLayoutBinding(layoutResource: Int) : ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(this.context), layoutResource, this, false)
}
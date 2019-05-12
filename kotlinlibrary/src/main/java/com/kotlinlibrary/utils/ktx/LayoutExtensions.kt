package com.kotlinlibrary.utils.ktx

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun Context.inflate(
    @LayoutRes layoutId: Int,
    root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = layoutInflater!!.inflate(layoutId, root, attachToRoot)

fun ViewGroup.inflateInto(
        @LayoutRes layoutReId: Int,
        attachToRoot: Boolean = false
): View = context.inflate(layoutReId, this, attachToRoot)

fun ViewGroup.inflateView(@LayoutRes layoutRes: Int): View =
    context.inflate(layoutRes, this, false)

fun ViewGroup.inflateBindView(@LayoutRes layoutRes: Int): ViewDataBinding =
    DataBindingUtil.inflate(context.layoutInflater!!, layoutRes, this, false)

fun <VDS : ViewDataBinding> ViewGroup.inflateCustomBindView(@LayoutRes layoutRes: Int): VDS {
    return (DataBindingUtil.inflate(context.layoutInflater!!, layoutRes, this, false) as VDS)
}

fun Context.inflateBindView(@LayoutRes layoutRes: Int): ViewDataBinding =
    DataBindingUtil.inflate(layoutInflater!!, layoutRes, null, false)

package com.kotlinlibrary.validation

import androidx.databinding.BaseObservable
import androidx.databinding.Observable

import java.io.Serializable

open class CustomObservableField<T, D : String> : BaseObservable, Serializable {
    private var mValue: T? = null
    private var mError: D? = null

    constructor(value: T, error: D) {
        mValue = value
        mError = error
    }

    constructor()

    constructor(vararg dependencies: Observable) {
        if (dependencies.isNotEmpty()) {
            val callback = DependencyCallback()

            for (dependency in dependencies) {
                dependency.addOnPropertyChangedCallback(callback)
            }
        }
    }

    open fun getValue(): T? = mValue

    open fun setValue(value: T?) {
        if (value != mValue) {
            mValue = value
            notifyChange()
        }
    }

    open fun getError(): D? = mError

    open fun setError(error: D?) {
        if (error != mError) {
            mError = error
            notifyChange()
        }
    }

    internal inner class DependencyCallback : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            notifyChange()
        }
    }

    companion object {
        internal const val serialVersionUID = 1L
    }
}
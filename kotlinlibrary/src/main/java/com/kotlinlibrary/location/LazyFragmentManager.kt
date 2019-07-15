package com.kotlinlibrary.location

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class LazyFragmentManager(private val instance: Any) : Lazy<FragmentManager> {

    private lateinit var manager: FragmentManager
    val tag = instance::class.java.simpleName

    override val value: FragmentManager
        get() {
            if (!isInitialized()) {
                manager = retrieveFragmentManager(instance)
            }
            return manager
        }

    override fun isInitialized(): Boolean = ::manager.isInitialized

    private fun retrieveFragmentManager(instance: Any): FragmentManager {
        return when (instance) {
            is FragmentActivity -> instance.supportFragmentManager
            is Fragment -> instance.childFragmentManager
            else -> throw Exception("No FragmentManager found in class ${instance::class.java.simpleName}")
        }
    }
}
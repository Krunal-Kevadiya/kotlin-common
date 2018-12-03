package com.kotlinlibrary.utils.resources

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment as SupportFragment

internal object SupportHelper {
  private val FQNAME_SUPPORT_FRAGMENT = "androidx.fragment.app.Fragment"
  private val FQNAME_RECYCLER_HOLDER = "androidx.recyclerview.widget.RecyclerView.ViewHolder"

  private val HAS_SUPPORT_FRAGMENTS = hasClass(FQNAME_SUPPORT_FRAGMENT)
  private val HAS_RECYCLER_HOLDER = hasClass(FQNAME_RECYCLER_HOLDER)

  internal fun isFragment(target: Any): Boolean {
    return HAS_SUPPORT_FRAGMENTS && SupportFragmentHelper.isFragment(target)
  }

  internal fun isHolder(target: Any): Boolean {
    return HAS_RECYCLER_HOLDER && SupportRecyclerHelper.isHolder(target)
  }

  private fun hasClass(fqname: String): Boolean = try {
    Class.forName(fqname) != null
  } catch (exception: Exception) {
    false
  }
}

internal object SupportFragmentHelper {
  internal fun isFragment(target: Any): Boolean {
    return target is SupportFragment
  }

  internal fun getResources(target: Any): Resources {
    return (target as SupportFragment).resources
  }
}

internal object SupportRecyclerHelper {
  internal fun isHolder(target: Any): Boolean {
    return target is RecyclerView.ViewHolder
  }

  internal fun getResources(target: Any): Resources {
    return (target as RecyclerView.ViewHolder).itemView.resources
  }
}
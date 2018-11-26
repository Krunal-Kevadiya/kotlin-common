package com.kotlinlibrary.utils.arguments

import android.os.Bundle
import androidx.fragment.app.Fragment as SupportFragment

internal object SupportHelper {
  private val HAS_SUPPORT_FRAGMENTS = try {
    Class.forName("androidx.fragment.app.Fragment") != null
  } catch (exception: Exception) {
    false
  }

  internal fun isFragment(target: Any): Boolean {
    return HAS_SUPPORT_FRAGMENTS && SupportFragmentHelper.isFragment(target)
  }
}

internal object SupportFragmentHelper {
  internal fun isFragment(target: Any): Boolean {
    return target is SupportFragment
  }

  internal fun getArguments(target: Any): Bundle? {
    return (target as SupportFragment).arguments
  }

  internal fun setArguments(target: Any, bundle: Bundle) {
    (target as SupportFragment).arguments = bundle
  }
}
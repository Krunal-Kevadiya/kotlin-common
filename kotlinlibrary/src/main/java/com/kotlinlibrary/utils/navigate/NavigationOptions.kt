package com.kotlinlibrary.utils.navigate

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.kotlinlibrary.R

class NavigationOptions internal constructor() {
    var transactionType: TransactionType = TransactionType.FragmentType.Add
    var addToBackStack: Boolean = false
    var tag: String = ""

    internal var animOption: AnimOption = AnimOption()
    fun animRequest(func: AnimOption.() -> Unit) {
        animOption = AnimOption().apply(func)
    }

    internal var targetOption: TargetOption = TargetOption()
    fun targetRequest(func: TargetOption.() -> Unit) {
        targetOption = TargetOption().apply(func)
    }

    internal var childFragmentOption: ChildFragmentOption = ChildFragmentOption()
    fun childFragmentRequest(func: ChildFragmentOption.() -> Unit) {
        childFragmentOption = ChildFragmentOption().apply(func)
    }
}

class AnimOption internal constructor() {
    var animEnter: Int = R.anim.fade_in
    var animExit: Int = R.anim.fade_out
    var views: MutableList<Pair<View, String>>? = null
}

class TargetOption internal constructor() {
    var fragmentToTarget: Fragment? = null
    var requestCode: Int = 0
    var targetDialogFragment: DialogFragment? = null
}

class ChildFragmentOption internal constructor() {
    var parentFragment: Fragment? = null
}
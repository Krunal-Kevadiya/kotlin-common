package com.kotlinlibrary.skeletonlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewReplacer(private val sourceView: View) {
    var targetView: View? = null
        private set
    private var mTargetViewResID = -1
    private var currentView: View? = null
    private var mSourceParentView: ViewGroup? = null
    private val mSourceViewLayoutParams: ViewGroup.LayoutParams = this.sourceView.layoutParams
    private var mSourceViewIndexInParent = 0
    private val mSourceViewId: Int

    init {
        currentView = this.sourceView
        mSourceViewId = this.sourceView.id
    }

    fun replace(targetViewResID: Int) {
        if (mTargetViewResID == targetViewResID) {
            return
        }
        if (init()) {
            mTargetViewResID = targetViewResID
            replace(LayoutInflater.from(sourceView.context).inflate(mTargetViewResID, mSourceParentView, false))
        }
    }

    fun replace(targetView: View) {
        if (currentView === targetView) {
            return
        }
        if (targetView.parent != null) {
            (targetView.parent as ViewGroup).removeView(targetView)
        }
        if (init()) {
            this.targetView = targetView
            mSourceParentView?.removeView(currentView)
            this.targetView?.id = mSourceViewId
            mSourceParentView?.addView(this.targetView, mSourceViewIndexInParent, mSourceViewLayoutParams)
            currentView = this.targetView
        }
    }

    fun restore() {
        if (mSourceParentView != null) {
            mSourceParentView?.removeView(currentView)
            mSourceParentView?.addView(sourceView, mSourceViewIndexInParent, mSourceViewLayoutParams)
            currentView = sourceView
            targetView = null
            mTargetViewResID = -1
        }
    }

    private fun init(): Boolean {
        if (mSourceParentView == null) {
            mSourceParentView = sourceView.parent as ViewGroup
            if (mSourceParentView == null) {
                return false
            }
            val count = mSourceParentView!!.childCount
            for (index in 0 until count) {
                if (sourceView === mSourceParentView!!.getChildAt(index)) {
                    mSourceViewIndexInParent = index
                    break
                }
            }
        }
        return true
    }
}

package com.kotlincommon

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.skeletonlayout.Skeleton
import com.kotlinlibrary.skeletonlayout.SkeletonLayout
import com.kotlinlibrary.skeletonlayout.applySkeleton

class SkeletonViewActivity : AppCompatActivity() {
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skeleton_view)

        skeleton = findViewById<SkeletonLayout>(R.id.skeletonLayout)
        // or create a new SkeletonLayout from a given View
        //skeleton = view.createSkeleton()

        skeleton.showSkeleton()

        Handler().postDelayed({
            skeleton.showOriginal()
        }, 5000)

        supportFragmentManager.beginTransaction().add(R.id.frameLayout, SkeletonFragment.newInstance()).commit()
    }

    override fun onPause() {
        super.onPause()
        skeleton.showOriginal()
    }
}

class SkeletonFragment : Fragment() {
    companion object {
        fun newInstance(): SkeletonFragment = SkeletonFragment()
    }
    private lateinit var skeleton: Skeleton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_recycler_adapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skeleton = view.findViewById<RecyclerView>(R.id.recyclerView).applySkeleton(R.layout.item_recycler, 5)
        skeleton.showSkeleton()

        Handler().postDelayed({
            skeleton.showOriginal()
        }, 5000)
    }

    override fun onPause() {
        super.onPause()
        skeleton.showOriginal()
    }
}
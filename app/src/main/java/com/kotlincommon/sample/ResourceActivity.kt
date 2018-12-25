package com.kotlincommon.sample

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlinlibrary.utils.resources.bindResource

class ResourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        supportFragmentManager.beginTransaction().add(R.id.frameLayout,
            ResourcesFragment.newInstance()
        ).commit()
    }
}

class ResourcesFragment : Fragment() {
    companion object {
        fun newInstance(): ResourcesFragment = ResourcesFragment()
    }

    // Boolean resource binding
    val boolean by bindResource<Boolean>(R.bool.boolean_resource)

    // Color resource binding
    val color by bindResource<Int>(R.color.colorAccent)

    // Drawable resource binding #1
    val bitmap by bindResource<BitmapDrawable>(R.mipmap.ic_launcher)

    // Drawable resource binding #2
    val vector by bindResource<VectorDrawable>(R.drawable.ic_close)

    // Dimension resource binding
    val dimension by bindResource<Float>(R.dimen._5ssp)

    // String resource binding
    val string by bindResource<String>(R.string.app_name)

    private var textView: TextView? = null
    private var imageView: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_resources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById(R.id.textView)
        imageView = view.findViewById(R.id.imageView)

        if(boolean) {
            imageView?.setImageDrawable(bitmap)
        } else {
            imageView?.setImageDrawable(vector)
        }
        imageView?.setColorFilter(color)

        textView?.setTextColor(color)
        textView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, dimension)
        textView?.text = string
    }
}

/**********************************************************
// Method
bindResource<Boolean>(R.boolean.boolean_resource)
bindResource<Int>(R.integer.integer_resource)
bindResource<Int>(R.color.color_resource)
bindResource<ColorStateList>(R.color.color_resource)
bindResource<Drawable>(R.Drawable.drawable_resource)
bindResource<Int>(R.dimen.dimen_resource)
bindResource<Float>(R.dimen.dimen_resource)
bindResource<String>(R.string.string_resource)
bindResource<CharSequence>(R.string.string_resource)
bindResource<IntArray>(R.array.array_resource)
bindResource<Array<String>>(R.array.array_resource)
bindResource<Array<CharSequence>>(R.array.array_resource)
**********************************************************/
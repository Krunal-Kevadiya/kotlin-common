package com.kotlincommon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlinlibrary.utils.preferences.PreferencesAware
import com.kotlinlibrary.utils.preferences.bindGsonPreference
import com.kotlinlibrary.utils.preferences.bindOptionalPreference
import com.kotlinlibrary.utils.preferences.bindPreference

class PreferenceActivity : AppCompatActivity() {

    // Boolean preference
    var boolean by bindPreference<Boolean>(false, "boolean")

    // Float preference
    var float by bindPreference<Float>(0.0f, "float")

    // Integer preference
    var integer by bindPreference<Int>(1, "integer")

    // Long preference
    var long by bindPreference<Long>(1L, "long")

    // String preference
    var string by bindPreference<String>("default", "string")

    var profile by bindGsonPreference(Profile())

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        findViewById<TextView>(R.id.textView).text =
                """From Activity
                boolean -> $boolean
                float -> $float
                integer -> $integer
                long -> $long
                string -> $string
                profile -> $profile
                """.trimIndent()

        supportFragmentManager.beginTransaction().add(R.id.frameLayout, PreferencesFragment.newInstance()).commit()
    }
}

class PreferencesFragment : Fragment() {
    companion object {
        fun newInstance(): PreferencesFragment = PreferencesFragment()
    }

    val preferences = PreferencesAware {
        activity!!.getSharedPreferences("CustomSharedPreferences", Context.MODE_PRIVATE)
    }

    var boolean by preferences.bindPreference<Boolean>(false, "boolean")
    var float by preferences.bindPreference<Float>(0.0f, "float")
    var integer by preferences.bindPreference<Int>(1, "integer")
    var long by preferences.bindPreference<Long>(1L, "long")
    var string by preferences.bindPreference<String>("default", "string")

    // Optional preferences are supported as well
    var optionalLong by preferences.bindOptionalPreference<Long>()
    var optionalString by preferences.bindOptionalPreference<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_preference, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text =
                """From Fragment
                boolean -> $boolean
                float -> $float
                integer -> $integer
                long -> $long
                string -> $string
                optionalLong -> $optionalLong
                optionalString -> $optionalString
                """.trimIndent()
    }
}

//Gson
data class Profile(val firstName: String? = null, val lastName: String? = null)

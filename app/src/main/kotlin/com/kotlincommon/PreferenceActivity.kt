package com.kotlincommon

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        logs("boolean $boolean", LogType.ERROR)
        logs("float $float", LogType.ERROR)
        logs("integer $integer", LogType.ERROR)
        logs("long $long", LogType.ERROR)
        logs("string $string", LogType.ERROR)
        logs("profile $profile", LogType.ERROR)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logs("boolean $boolean", LogType.ERROR)
        logs("float $float", LogType.ERROR)
        logs("integer $integer", LogType.ERROR)
        logs("long $long", LogType.ERROR)
        logs("string $string", LogType.ERROR)
        logs("optionalLong $optionalLong", LogType.ERROR)
        logs("optionalString $optionalString", LogType.ERROR)
    }
}

//Gson
data class Profile(val firstName: String? = null, val lastName: String? = null)

package com.kotlincommon.sample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlinlibrary.utils.arguments.bindArgument
import com.kotlinlibrary.utils.arguments.bindOptionalArgument
import com.kotlinlibrary.utils.ktx.windowManager

class ArgumentActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, ArgumentActivity::class.java).apply {
            putExtra("firstName", "Krunal")
            putExtra("lastName", "Kevadiya")
        }
    }

    // extra name is automatically inferred from property name ("firstName" in this case)
    var firstName by bindArgument<String>("firstName")

    // you can also provide a default value using "default" named argument
    var lastName by bindArgument("lastName", "")

    var email by bindOptionalArgument<String>("email")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_argument)

        findViewById<TextView>(R.id.textView).text =
                """From Activity
                   firstName -> $firstName
                   lastName -> $lastName
                   email -> $email
                """.trimIndent()
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,
            UserProfileFragment.newInstance()
        ).commit()
    }
}

class UserProfileFragment : Fragment() {
    companion object {
        fun newInstance(): UserProfileFragment = UserProfileFragment().apply {
            this.firstName = "Krunal"
            this.lastName = "Kevadiya"
            this.email = null
        }
    }

    // extra name is automatically inferred from property name ("firstName" in this case)
    var firstName by bindArgument<String>()

    // you can also provide a default value using "default" named argument
    var lastName by bindArgument(default = "")

    var email by bindOptionalArgument<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_argument, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text =
                """From Fragment
                   firstName -> $firstName
                   lastName -> $lastName
                   email -> $email
                """.trimIndent()
    }
}
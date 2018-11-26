package com.kotlincommon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.arguments.bindArgument
import com.kotlinlibrary.utils.arguments.bindOptionalArgument
import com.kotlinlibrary.utils.logs

class ArgumentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_argument)

        supportFragmentManager.beginTransaction().add(R.id.frameLayout, UserProfileFragment.newInstance()).commit()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logs("firstName $firstName", LogType.ERROR)
        logs("lastName $lastName", LogType.ERROR)
        logs("email $email", LogType.ERROR)
    }

}



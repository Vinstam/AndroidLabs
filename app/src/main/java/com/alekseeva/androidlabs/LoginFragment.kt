package com.alekseeva.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import java.lang.Exception

class LoginFragment : Fragment() {
    // Поля ввода
    lateinit var loginField: EditText
    lateinit var passwordField: EditText
    lateinit var autologinCheckbox: CheckBox
    lateinit var loginButton: Button

    fun onLoginSuccess() {
        val navController = NavHostFragment.findNavController(this)
        setAutologin(requireActivity(), this.autologinCheckbox.isChecked)
        navController.navigate(R.id.fragmentOne)
    }

    fun onLoginError(exception: Exception) {
        Toast.makeText(requireActivity().applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        loginButton = root.findViewById(R.id.loginButton)
        loginField = root.findViewById(R.id.login)
        passwordField = root.findViewById(R.id.password)
        autologinCheckbox = root.findViewById(R.id.autologin)

        loginButton.setOnClickListener({
            val login = loginField.text.toString()
            val password = passwordField.text.toString()

            logIn(login, password, ::onLoginSuccess, ::onLoginError)
        })

        return root
    }
}
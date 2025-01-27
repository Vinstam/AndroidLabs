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

class LoginFragment : Fragment() {
    // Поля ввода
    lateinit var loginField: EditText
    lateinit var passwordField: EditText
    lateinit var autologinCheckbox: CheckBox
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val sharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val navController = NavHostFragment.findNavController(this)

        loginButton = root.findViewById(R.id.loginButton)
        loginField = root.findViewById(R.id.login)
        passwordField = root.findViewById(R.id.password)
        autologinCheckbox = root.findViewById(R.id.autologin)

        loginButton.setOnClickListener({
            val settingsLogin = sharedPreferences.getString("login", null)
            val settingsPassword = sharedPreferences.getString("password", null)

            if (!(settingsLogin == this.loginField.text.toString() &&
                        settingsPassword == this.passwordField.text.toString())) {
                Toast.makeText(requireActivity().applicationContext, "Логин или пароль не совпадает", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }

            sharedPreferences.edit().putBoolean("autologin", this.autologinCheckbox.isChecked).apply()
            navController.navigate(R.id.fragment_1)
        })

        return root
    }
}
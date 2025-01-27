package com.alekseeva.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    // Поля ввода
    lateinit var loginField: EditText
    lateinit var passwordField: EditText
    lateinit var autologinCheckbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)

        this.loginField = findViewById(R.id.login)
        this.passwordField = findViewById(R.id.password)
        this.autologinCheckbox = findViewById(R.id.autologin)

        loginButton.setOnClickListener({
            val settingsLogin = sharedPreferences.getString("login", null)
            val settingsPassword = sharedPreferences.getString("password", null)

            if (!(settingsLogin == this.loginField.text.toString() &&
                settingsPassword == this.passwordField.text.toString())) {
                Toast.makeText(applicationContext, "Логин или пароль не совпадает", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }

            sharedPreferences.edit().putBoolean("autologin", this.autologinCheckbox.isChecked).apply()
            val intent = Intent()
            intent.setClass(applicationContext, ContentActivity::class.java)
            startActivity(intent)
        })
    }


}
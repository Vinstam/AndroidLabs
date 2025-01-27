package com.alekseeva.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity() {
    // Поля ввода
    lateinit var loginField: EditText
    lateinit var passwordField: EditText
    lateinit var repeatPasswordField: EditText

    fun validatePassword(originText: String, repeatText: String): Boolean {
        return originText.length >= 8 && originText == repeatText
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateNumber(number: String): Boolean {
        return Pattern.compile("\\+\\d+").matcher(number).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val phoneNumberButton = findViewById<Button>(R.id.phoneNumberButton)
        val emailNumberButton = findViewById<Button>(R.id.emailNumberButton)
        val registrationButton = findViewById<Button>(R.id.registrationButton)
        this.loginField = findViewById(R.id.login)
        this.passwordField = findViewById(R.id.password)
        this.repeatPasswordField = findViewById(R.id.passwordRepeat)
        loginField.inputType =InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        phoneNumberButton.setOnClickListener({
            phoneNumberButton.setTextColor(resources.getColor(R.color.primary))
            emailNumberButton.setTextColor(resources.getColor(R.color.textSecondary))
            this.loginField.setHint("Введите номер")
            this.loginField.setInputType(InputType.TYPE_CLASS_PHONE)
            this.loginField.setText("")
        })

        emailNumberButton.setOnClickListener({
            emailNumberButton.setTextColor(resources.getColor(R.color.primary))
            phoneNumberButton.setTextColor(resources.getColor(R.color.textSecondary))
            this.loginField.setHint("Введите email")
            this.loginField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
            this.loginField.setText("")
        })

        registrationButton.setOnClickListener({
            val login = this.loginField.text.toString()
            val password = this.passwordField.text.toString()

            val isLoginPassedValidation = if
                    (this.loginField.inputType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                        this.validateEmail(login)
                    else this.validateNumber(login)

            val isPasswordPassedValidation =
                this.validatePassword(password, this.repeatPasswordField.text.toString())

            if (!(isLoginPassedValidation && isPasswordPassedValidation)) {
                Toast.makeText(applicationContext, "Введите корректные значения", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("login", login).apply()
            sharedPreferences.edit().putString("password", password).apply()

            val intent = Intent()
            intent.setClass(applicationContext, ContentActivity::class.java)
            startActivity(intent)
        })
    }

}
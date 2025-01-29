package com.alekseeva.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import java.lang.Exception
import java.util.regex.Pattern
class RegistrationFragment : Fragment() {
    lateinit var loginField: EditText
    lateinit var passwordField: EditText
    lateinit var repeatPasswordField: EditText
    lateinit var phoneNumberButton: Button
    lateinit var emailNumberButton: Button
    lateinit var registrationButton: Button

    fun validatePassword(originText: String, repeatText: String): Boolean {
        return originText.length >= 8 && originText == repeatText
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateNumber(number: String): Boolean {
        return Pattern.compile("\\+\\d+").matcher(number).matches()
    }

    fun onRegistrationSuccess() {
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.fragmentOne)
        setAutologin(requireActivity(), false)
    }

    fun onRegistrationError(exception: Exception) {
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
        val root = inflater.inflate(R.layout.fragment_registration, container, false)

        phoneNumberButton = root.findViewById(R.id.phoneNumberButton)
        emailNumberButton = root.findViewById(R.id.emailNumberButton)
        registrationButton = root.findViewById(R.id.registrationButton)
        loginField = root.findViewById(R.id.login)
        passwordField = root.findViewById(R.id.password)
        repeatPasswordField = root.findViewById(R.id.passwordRepeat)

        loginField.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

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
                Toast.makeText(requireActivity().applicationContext, "Введите корректные значения", Toast.LENGTH_LONG).show();
                return@setOnClickListener
            }

            createUser(login, password, ::onRegistrationSuccess, ::onRegistrationError)
        })

        return root
    }
}
package com.alekseeva.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val intent = Intent()
        if (sharedPreferences.all.isEmpty()) {
            intent.setClass(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }

        if (sharedPreferences.getString("login", null) != null &&
            sharedPreferences.getString("password", null) != null
            ) {
            if (sharedPreferences.getBoolean("autologin", false)) {
                intent.setClass(applicationContext, ContentActivity::class.java)
                startActivity(intent)
                return
            }

            intent.setClass(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
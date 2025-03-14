package com.alekseeva.androidlabs

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class ContentActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_content)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNav = findViewById(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{
            _, dist, _ ->
            when (dist.id){
                R.id.fragment_splash -> bottomNav.visibility = View.GONE
                R.id.fragment_registration -> bottomNav.visibility = View.GONE
                R.id.fragment_login -> bottomNav.visibility = View.GONE
                R.id.fragmentOne -> bottomNav.visibility = View.VISIBLE
                R.id.fragmentTwo -> bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        if (!isAutologin(this)) {
            signOut()
        }
        super.onStop()
    }
}
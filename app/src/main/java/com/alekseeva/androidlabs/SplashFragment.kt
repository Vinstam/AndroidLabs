package com.alekseeva.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        val navController = NavHostFragment.findNavController(this)
        val sharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)

        if (sharedPreferences.all.isEmpty()) {
            navController.navigate(R.id.fragment_registration)
        }

        if (sharedPreferences.getString("login", null) != null &&
            sharedPreferences.getString("password", null) != null
        ) {
            if (sharedPreferences.getBoolean("autologin", false)) {
                navController.navigate(R.id.fragment_1)
                return root
            }

            navController.navigate(R.id.fragment_login)
        }

        return root
    }
}
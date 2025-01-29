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

        val activity = requireActivity()
        if (!isRegistered(activity)) {
            navController.navigate(R.id.fragment_registration)
            return root
        }

        if (isSignedIn()) {
            navController.navigate(R.id.fragmentOne)
            return root
        }

        navController.navigate(R.id.fragment_login)
        return root
    }
}
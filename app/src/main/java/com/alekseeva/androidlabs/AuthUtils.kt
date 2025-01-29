package com.alekseeva.androidlabs

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth

fun createUser(
        login: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (exception: Exception) -> Unit
    ) {
    val auth = FirebaseAuth.getInstance()
    auth.createUserWithEmailAndPassword(login, password).addOnCompleteListener {
        task ->
            if (task.isSuccessful) onSuccess()
    }.addOnFailureListener({
        exception ->
            onError(exception)
    })
}

fun logIn(
    login: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (exception: Exception) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    auth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
            task ->
        if (task.isSuccessful) onSuccess()
    }.addOnFailureListener({
            exception ->
        onError(exception)
    })
}

fun signOut() {
    val auth = FirebaseAuth.getInstance()
    auth.signOut()
}

fun isSignedIn(): Boolean {
    val auth = FirebaseAuth.getInstance()
    return auth.currentUser != null
}

fun isRegistered(activity: FragmentActivity): Boolean {
    val sharedPreferences = activity.getSharedPreferences("settings", Context.MODE_PRIVATE)
    return sharedPreferences.all.isNotEmpty()
}

fun isAutologin(activity: FragmentActivity): Boolean {
    val sharedPreferences = activity.getSharedPreferences("settings", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("autologin", false)
}

fun setAutologin(activity: FragmentActivity, value: Boolean) {
    val sharedPreferences = activity.getSharedPreferences("settings", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("autologin", value).apply()
}



package com.ricardo.muzmatchexercise

import android.content.SharedPreferences
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val loggedInUserId = sharedPreferences.getInt("loggedInUserId", 0)
        if (loggedInUserId == 0) {
            // Logged in userId == 1000
            sharedPreferences.edit().putInt("loggedInUserId", 1000).apply()
        }
    }
}
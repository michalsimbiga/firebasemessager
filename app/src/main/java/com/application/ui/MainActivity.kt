package com.application.ui

import android.os.Bundle
import com.application.R
import com.google.firebase.FirebaseApp
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)
    }
}
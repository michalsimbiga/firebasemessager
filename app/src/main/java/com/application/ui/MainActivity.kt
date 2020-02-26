package com.application.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.application.R
import com.application.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}
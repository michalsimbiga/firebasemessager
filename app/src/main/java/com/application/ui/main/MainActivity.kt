package com.application.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.application.R
import com.application.databinding.ActivityMainBinding
import com.application.ui.base.BaseActivity
import timber.log.Timber

class MainActivity: BaseActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels { savedStateVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel.getJokes().observe(this, Observer {
            Timber.d("result: $it")
        })
    }
}
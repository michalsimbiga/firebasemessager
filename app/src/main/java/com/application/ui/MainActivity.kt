package com.application.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.application.R
import com.application.net.MyResult
import com.application.ui.base.BaseActivity
import com.application.vm.AssistedViewModelFactory
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    private val viewModel: MainViewModel by viewModels { savedStateVmFactory }

    private val responseObserver = Observer<MyResult<Boolean>> { response ->
        when (response) {
            is MyResult.Success -> navController.navigate(R.id.messages_fragment)
            is MyResult.Failure -> navController.navigate(R.id.login_fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel.loggedInResponse.observe(this, responseObserver)

        decideStartingPoint()
    }

    private fun decideStartingPoint() {
        viewModel.checkIfSignedIn()
    }
}
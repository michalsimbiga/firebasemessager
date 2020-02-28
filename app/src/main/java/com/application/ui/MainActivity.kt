package com.application.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.application.R
import com.application.ui.base.BaseActivity
import com.application.vm.AssistedViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: MainViewModel by viewModels { savedStateVmFactory }

    private val navController: NavController by lazy {
        findNavController(R.id.main_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() = with(navController) {
        when (currentDestination?.id) {
            R.id.nav_login_fragment -> finish()
            R.id.nav_register_fragment -> popBackStack(R.id.nav_login_fragment, false)
            R.id.nav_messages_fragment -> finish()

            else -> if (popBackStack().not()) finish()
        }
        Unit
    }
}
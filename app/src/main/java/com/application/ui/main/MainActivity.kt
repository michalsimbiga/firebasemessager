package com.application.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.application.R
import com.application.ui.base.BaseActivity
import com.application.domain.common.AssistedViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: MainViewModel by viewModels { savedStateVmFactory }

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initNavGraph()
    }

    private fun initNavGraph() {
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.main_navigation)
        navController.graph = graph
    }

    override fun onBackPressed() = with(navController) {
        when (currentDestination?.id) {
            R.id.nav_login_fragment -> finish()
            R.id.nav_register_fragment -> popBackStack(R.id.nav_login_fragment, false)
            R.id.nav_messages_fragment -> finish()
            R.id.nav_new_message_fragment -> navigate(R.id.nav_messages_fragment)
            R.id.nav_chat_fragment -> navigate(R.id.nav_messages_fragment)

            else -> if (popBackStack().not()) finish()
        }
        Unit
    }
}
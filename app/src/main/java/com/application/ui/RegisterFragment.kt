package com.application.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.application.databinding.RegisterFragmentBinding
import com.application.extensions.popBackstack
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.register_fragment.*
import timber.log.Timber
import javax.inject.Inject

class RegisterFragment : DaggerFragment() {


    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel : RegisterViewModel by viewModels { vmFactory }

    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RegisterFragmentBinding.inflate(inflater, container, false)

        with(binding) {
            viewModel = this@RegisterFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_login.setOnClickListener { popBackstack() }
        register_button.setOnClickListener {viewModel.register()}
    }


}
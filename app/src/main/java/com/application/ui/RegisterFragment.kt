package com.application.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.databinding.RegisterFragmentBinding
import com.application.extensions.popBackstack
import com.application.net.MyResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.register_fragment.*
import timber.log.Timber
import javax.inject.Inject

class RegisterFragment : DaggerFragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: RegisterViewModel by viewModels { vmFactory }

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

        viewModel.responseLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is MyResult.Success -> {
                    Timber.i("TESTING response success ${result.data}")
                }
                is MyResult.Failure -> {
                    Timber.i("TESTING response failure ${result.exception}")
                }
                is MyResult.Loading -> {
                    register_button.isEnabled = result.isLoading.not()
                    Timber.i("TESTING response loading ${result.isLoading}")
                }
            }
        })

        register_login.setOnClickListener { popBackstack() }
        register_button.setOnClickListener { viewModel.register() }
    }


}
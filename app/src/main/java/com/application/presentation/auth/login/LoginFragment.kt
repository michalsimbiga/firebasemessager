package com.application.presentation.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.application.R
import com.application.data.model.User
import com.application.databinding.FragmentLoginBinding
import com.application.domain.extensions.navigateTo
import com.application.domain.net.MyResult
import com.application.presentation.base.BaseFragment
import com.application.domain.common.AssistedViewModelFactory
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: LoginViewModel by viewModels { savedStateVmFactory }

    private var binding: FragmentLoginBinding? = null

    private val loggedInResponseObserver = Observer<MyResult<User>> { response ->
        if (response is MyResult.Success) navigateTo(R.id.nav_messages_fragment)
    }

    private val loginResponseObserver = Observer<MyResult<Any>> { response ->
        if (response is MyResult.Success) navigateTo(R.id.nav_messages_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() = with(binding) {
        this?.loginRegister?.setOnClickListener { navigateTo(R.id.nav_register_fragment) }
        this?.loginButton?.setOnClickListener { viewModel.logIn() }
    }

    private fun setupObservers() = with(viewModel) {
        signedInResponse.observe(viewLifecycleOwner, loggedInResponseObserver)
        loginResponse.observe(viewLifecycleOwner, loginResponseObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
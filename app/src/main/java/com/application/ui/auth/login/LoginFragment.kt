package com.application.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.application.R
import com.application.databinding.FragmentLoginBinding
import com.application.extensions.navigateTo
import com.application.net.MyResult
import com.application.ui.base.BaseFragment
import com.application.domain.common.AssistedViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: LoginViewModel by viewModels { savedStateVmFactory }

    private var binding: FragmentLoginBinding? = null

    private val loggedInResponseObserver = Observer<MyResult<Boolean>> { response ->
        when (response) {
            is MyResult.Success -> {
                if (response.data) navigateTo(R.id.nav_messages_fragment)
            }
        }
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

        viewModel.signedInResponse.observe(viewLifecycleOwner, loggedInResponseObserver)
        login_register.setOnClickListener { navigateTo(R.id.nav_register_fragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
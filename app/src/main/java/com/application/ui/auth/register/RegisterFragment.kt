package com.application.ui.auth.register

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.application.R
import com.application.databinding.FragmentRegisterBinding
import com.application.extensions.navigateTo
import com.application.extensions.popBackstack
import com.application.extensions.showSnack
import com.application.net.MyResult
import com.application.ui.base.BaseFragment
import com.application.vm.AssistedViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

class RegisterFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: RegisterViewModel by viewModels { savedStateVmFactory }

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

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
                is MyResult.Success -> navigateTo(R.id.nav_messages_fragment)
                is MyResult.Failure -> showSnack(result.message)
                is MyResult.Loading -> register_button.isEnabled = result.isLoading.not()
            }
        })

        register_login.setOnClickListener { popBackstack() }
        register_button.setOnClickListener { viewModel.register() }

        user_image.setOnClickListener { startPhotoPicker() }
    }

    private fun startPhotoPicker() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        startActivityForResult(
            intent,
            PHOTO_SELECT_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PHOTO_SELECT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            viewModel.photoUri = data?.data ?: Uri.EMPTY
            showUserPicture()
        }
    }

    private fun showUserPicture() =
        Glide
            .with(requireContext())
            .load(viewModel.photoUri)
            .centerCrop()
            .into(user_image)

    companion object {
        private const val PHOTO_SELECT_REQUEST_CODE = 2020
    }
}
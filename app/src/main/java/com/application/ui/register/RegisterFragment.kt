package com.application.ui.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.application.databinding.FragmentRegisterBinding
import com.application.extensions.popBackstack
import com.application.net.MyResult
import com.application.ui.base.BaseFragment
import com.application.vm.AssistedViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_register.*
import timber.log.Timber
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
                is MyResult.Success -> Timber.i("TESTING response success ${result.data}")
                is MyResult.Failure -> Timber.i("TESTING response failure ${result.exception}")
                is MyResult.Loading -> {
                    register_button.isEnabled = result.isLoading.not()
                    Timber.i("TESTING response loading ${result.isLoading}")
                }
            }
        })

        register_login.setOnClickListener { popBackstack() }
        register_button.setOnClickListener { viewModel.register() }

        user_image.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(
                intent,
                PHOTO_SELECT_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PHOTO_SELECT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            viewModel.photoUri = data?.data
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
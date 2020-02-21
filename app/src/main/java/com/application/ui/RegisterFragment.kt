package com.application.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.databinding.RegisterFragmentBinding
import com.application.extensions.popBackstack
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.register_fragment.*
import timber.log.Timber

class RegisterFragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RegisterFragmentBinding.inflate(inflater, container, false)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_login.setOnClickListener { popBackstack() }

        register_button.setOnClickListener {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { Timber.i("TESTING complete $it") }
                .addOnSuccessListener {  Timber.i("TESTING success $it") }
                .addOnFailureListener {  Timber.i("TESTING failure $it") }
        }
    }


}
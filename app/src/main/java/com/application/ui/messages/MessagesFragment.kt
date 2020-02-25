package com.application.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.application.databinding.FragmentMessagesBinding
import com.application.vm.AssistedViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MessagesFragment : DaggerFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: MessagesViewModel by viewModels { savedStateVmFactory }

    private lateinit var binding: FragmentMessagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMessagesBinding.inflate(inflater, container, false)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }
}
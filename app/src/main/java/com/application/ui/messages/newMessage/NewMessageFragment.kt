package com.application.ui.messages.newMessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.application.databinding.FragmentNewMessageBinding
import com.application.ui.base.BaseFragment
import com.application.vm.AssistedViewModelFactory
import javax.inject.Inject

class NewMessageFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: NewMessageViewModel by viewModels { savedStateVmFactory }

    private lateinit var binding: FragmentNewMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentNewMessageBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.newMessageFragmentRecyclerView.adapter =
    }
}
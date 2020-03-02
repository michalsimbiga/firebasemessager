package com.application.ui.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.application.databinding.FragmentChatBinding
import com.application.databinding.FragmentNewMessageBinding
import com.application.model.User
import com.application.net.MyResult
import com.application.ui.base.BaseFragment
import com.application.ui.messages.newMessage.NewMessageRecyclerAdapter
import com.application.ui.messages.newMessage.NewMessageViewModel
import com.application.vm.AssistedViewModelFactory
import javax.inject.Inject

class ChatFragment : BaseFragment() {
    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: NewMessageViewModel by viewModels { savedStateVmFactory }
    private val recyclerAdapter by lazy { ChatRecyclerViewAdapter() }

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentChatBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }
}
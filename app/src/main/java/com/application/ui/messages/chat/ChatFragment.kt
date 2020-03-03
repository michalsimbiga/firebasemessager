package com.application.ui.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.application.data.model.Message
import com.application.databinding.FragmentChatBinding
import com.application.domain.common.AssistedViewModelFactory
import com.application.extensions.empty
import com.application.ui.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class ChatFragment : BaseFragment() {
    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: ChatViewModel by viewModels { savedStateVmFactory }
    private val recyclerAdapter by lazy { ChatRecyclerViewAdapter() }

    private val args: ChatFragmentArgs by navArgs()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setNewReceipient(args.chatRecipient)
        setupSendButtonListener()
    }

    private fun setupSendButtonListener() {
        binding.fragmentChatSendButton.setOnClickListener { sendMessage(binding.fragmentChatSendMessage.text.toString()) }
    }

    private fun sendMessage(text: String) {
        viewModel.sendMessage(text)
        binding.fragmentChatSendMessage.setText(String.empty)
    }
}
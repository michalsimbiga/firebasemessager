package com.application.presentation.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.application.data.model.Message
import com.application.databinding.FragmentChatBinding
import com.application.domain.common.AssistedViewModelFactory
import com.application.domain.extensions.empty
import com.application.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject

class ChatFragment : BaseFragment() {
    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: ChatViewModel by viewModels { savedStateVmFactory }
    private val recyclerAdapter by lazy { ChatRecyclerViewAdapter() }

    private val args: ChatFragmentArgs by navArgs()

    private var binding: FragmentChatBinding? = null

    private val messagesObserver = Observer<Message> { newMessage ->
        recyclerAdapter.addMessage(newMessage)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentChatBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        setHasOptionsMenu(true)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.messages.observe(viewLifecycleOwner, messagesObserver)

        viewModel.setNewRecipient(args.chatRecipient)
        recyclerAdapter.setRecipient(viewModel.recipient)
        fragment_chat_recycler_view.adapter = recyclerAdapter
        setupSendButtonListener()
    }

    private fun setupSendButtonListener() {
        binding?.fragmentChatSendButton?.setOnClickListener { sendMessage(binding?.fragmentChatSendMessage?.text.toString()) }
    }

    private fun sendMessage(text: String) {
        viewModel.sendMessage(text)
        binding?.fragmentChatSendMessage?.setText(String.empty)
    }

    override fun onDestroyView() {
        fragment_chat_recycler_view.adapter = null
        super.onDestroyView()
        binding = null
    }
}
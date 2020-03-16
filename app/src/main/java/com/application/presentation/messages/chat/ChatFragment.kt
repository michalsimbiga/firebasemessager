package com.application.presentation.messages.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.application.data.model.Message
import com.application.data.model.User
import com.application.databinding.FragmentChatBinding
import com.application.domain.common.AssistedViewModelFactory
import com.application.domain.extensions.clear
import com.application.domain.extensions.empty
import com.application.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject

class ChatFragment : BaseFragment() {
    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: ChatViewModel by viewModels { savedStateVmFactory }
    private val recyclerAdapter = ChatRecyclerViewAdapter()

    private val args: ChatFragmentArgs by navArgs()

    private var binding: FragmentChatBinding? = null

    private val messagesObserver = Observer<MutableList<Message>> { newList ->
        recyclerAdapter.addMessages(newList).also {
            fragment_chat_recycler_view.smoothScrollToPosition(recyclerAdapter.itemCount)
        }
    }

    private val currentUserObserver =
        Observer<User> { user -> recyclerAdapter.setCurrentUser(user) }

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

        setRecipient(args.chatRecipient)
        setupObservers()
        setupRecycler()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() = with(binding) {
        this?.fragmentChatSendButton?.setOnClickListener {
            sendMessage(this.fragmentChatSendMessage.text.toString())
        }
    }

    private fun setupObservers() = with(viewModel) {
        messages.observe(viewLifecycleOwner, messagesObserver)
        currentUser.observe(viewLifecycleOwner, currentUserObserver)
    }

    private fun setRecipient(user: User) {
        viewModel.setNewRecipient(user)
        recyclerAdapter.setRecipient(user)
    }

    private fun setupRecycler() = with(binding) {
        this?.fragmentChatRecyclerView?.adapter = recyclerAdapter
    }

    private fun sendMessage(text: String) {
        viewModel.sendMessage(text)
        binding?.fragmentChatSendMessage?.clear()
    }

    override fun onDestroyView() {
        fragment_chat_recycler_view.adapter = null
        super.onDestroyView()
        binding = null
    }
}
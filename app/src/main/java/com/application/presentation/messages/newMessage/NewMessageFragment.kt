package com.application.presentation.messages.newMessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.application.databinding.FragmentNewMessageBinding
import com.application.data.model.User
import com.application.domain.net.MyResult
import com.application.presentation.base.BaseFragment
import com.application.domain.common.AssistedViewModelFactory
import kotlinx.android.synthetic.main.fragment_new_message.*
import javax.inject.Inject

class NewMessageFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: NewMessageViewModel by viewModels { savedStateVmFactory }
    private val recyclerAdapter by lazy { NewMessageRecyclerAdapter() }

    private var binding: FragmentNewMessageBinding? = null

    private val usersResponseObserver = Observer<MyResult<List<User>>> { result ->
        when (result) {
            is MyResult.Success -> recyclerAdapter.setNewData(result.data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentNewMessageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        setHasOptionsMenu(true)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupOnClickListeners()
        setupRecycler()
    }

    private fun setupOnClickListeners() = with(binding) {
        recyclerAdapter.setOnUserItemClickListener { user ->
            val action =
                NewMessageFragmentDirections.actionNavNewMessageFragmentToNavChatFragment(user)
            findNavController().navigate(action)
        }
    }

    private fun setupObservers() = with(viewModel) {
        userDataResponse.observe(viewLifecycleOwner, usersResponseObserver)
    }

    private fun setupRecycler() = with(binding) {
        this?.newMessageFragmentRecyclerView?.adapter = recyclerAdapter
    }

    override fun onDestroyView() {
        binding?.newMessageFragmentRecyclerView?.adapter = null
        binding = null
        super.onDestroyView()
    }
}
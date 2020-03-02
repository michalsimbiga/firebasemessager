package com.application.ui.messages.newMessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.application.databinding.FragmentNewMessageBinding
import com.application.model.User
import com.application.net.MyResult
import com.application.ui.base.BaseFragment
import com.application.vm.AssistedViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class NewMessageFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: NewMessageViewModel by viewModels { savedStateVmFactory }
    private val recyclerAdapter by lazy { NewMessageRecyclerAdapter() }

    private lateinit var binding: FragmentNewMessageBinding

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

        binding = FragmentNewMessageBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userDataResponse.observe(viewLifecycleOwner, usersResponseObserver)

        recyclerAdapter.setOnUserItemClickListener { user ->
            val action =
                NewMessageFragmentDirections.actionNavNewMessageFragmentToNavChatFragment(user)
            findNavController().navigate(action)
        }

        binding.newMessageFragmentRecyclerView.adapter = recyclerAdapter
    }
}
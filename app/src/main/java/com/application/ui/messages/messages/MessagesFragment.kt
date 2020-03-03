package com.application.ui.messages.messages

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.application.R
import com.application.databinding.FragmentMessagesBinding
import com.application.extensions.navigateTo
import com.application.ui.base.BaseFragment
import com.application.domain.common.AssistedViewModelFactory
import javax.inject.Inject

class MessagesFragment : BaseFragment() {

    @Inject
    lateinit var savedStateVmFactory: AssistedViewModelFactory

    private val viewModel: MessagesViewModel by viewModels { savedStateVmFactory }

    private var binding: FragmentMessagesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMessagesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        setHasOptionsMenu(true)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.messagesFragmentFab?.setOnClickListener {
            navigateTo(R.id.nav_new_message_fragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sign_out -> {
                viewModel.signOut()
                findNavController().navigate(R.id.nav_login_fragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
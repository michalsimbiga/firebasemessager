package com.application.di.module.uimodules

import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.application.di.ViewModelKey
import com.application.di.module.ViewModelAssistedFactory
import com.application.presentation.messages.chat.ChatFragment
import com.application.presentation.messages.chat.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChatModule{

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModelFactory(viewModel: ChatViewModel.Factory): ViewModelAssistedFactory<out ViewModel>

    @Binds
    abstract fun bindChatSavedStateRegistryOwner(chatFragment: ChatFragment): SavedStateRegistryOwner
}
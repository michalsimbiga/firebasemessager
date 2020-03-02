package com.application.di.module.uimodules

import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.application.di.ViewModelKey
import com.application.di.module.ViewModelAssistedFactory
import com.application.ui.messages.MessagesFragment
import com.application.ui.messages.MessagesViewModel
import com.application.ui.messages.chat.ChatFragment
import com.application.ui.messages.chat.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MessagesModule {

    @Binds
    @IntoMap
    @ViewModelKey(MessagesViewModel::class)
    abstract fun bindRegisterViewModelFactory(viewModel: MessagesViewModel.Factory): ViewModelAssistedFactory<out ViewModel>

    @Binds
    abstract fun bindRegisterSavedStateRegistryOwner(messagesFragment: MessagesFragment): SavedStateRegistryOwner
}
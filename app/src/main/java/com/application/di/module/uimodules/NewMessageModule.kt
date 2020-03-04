package com.application.di.module.uimodules

import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.application.di.ViewModelKey
import com.application.di.module.ViewModelAssistedFactory
import com.application.presentation.messages.newMessage.NewMessageFragment
import com.application.presentation.messages.newMessage.NewMessageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewMessageModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewMessageViewModel::class)
    abstract fun bindRegisterViewModelFactory(viewModel: NewMessageViewModel.Factory): ViewModelAssistedFactory<out ViewModel>

    @Binds
    abstract fun bindRegisterSavedStateRegistryOwner(newMessageFragment: NewMessageFragment): SavedStateRegistryOwner
}
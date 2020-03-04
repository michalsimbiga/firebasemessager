package com.application.di.module.uimodules

import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.application.di.ViewModelKey
import com.application.di.module.ViewModelAssistedFactory
import com.application.presentation.auth.register.RegisterFragment
import com.application.presentation.auth.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RegisterModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModelFactory(viewModel: RegisterViewModel.Factory): ViewModelAssistedFactory<out ViewModel>

    @Binds
    abstract fun bindRegisterSavedStateRegistryOwner(registerFragment: RegisterFragment): SavedStateRegistryOwner
}
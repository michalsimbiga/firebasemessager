package com.application.di.module.mainActivity

import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.application.di.ViewModelKey
import com.application.di.module.ViewModelAssistedFactory
import com.application.ui.RegisterFragment
import com.application.ui.RegisterViewModel
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
    abstract fun bindRegisterSavedStateRegistyOwner(registerFragment: RegisterFragment): SavedStateRegistryOwner
}
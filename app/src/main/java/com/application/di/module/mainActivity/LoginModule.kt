package com.application.di.module.mainActivity

import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.application.di.ViewModelKey
import com.application.di.module.ViewModelAssistedFactory
import com.application.ui.login.LoginFragment
import com.application.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModelFactory(viewModel: LoginViewModel.Factory): ViewModelAssistedFactory<out ViewModel>

    @Binds
    abstract fun bindLoginSavedStateRegistryOwner(loginFragment: LoginFragment): SavedStateRegistryOwner
}
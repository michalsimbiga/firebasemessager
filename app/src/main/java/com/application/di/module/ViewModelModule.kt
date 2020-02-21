package com.application.di.module

import androidx.lifecycle.ViewModel
import com.application.di.ViewModelKey
import com.application.ui.LoginViewModel
import com.application.ui.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun registerViewModel(): ViewModel = RegisterViewModel()

    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModel(): ViewModel = LoginViewModel()

}
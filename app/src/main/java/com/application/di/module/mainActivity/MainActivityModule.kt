package com.application.di.module.mainActivity

import com.application.ui.LoginFragment
import com.application.ui.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment


    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment
}
package com.application.di

import com.application.di.module.UiModules.LoginModule
import com.application.di.module.UiModules.MainModule
import com.application.di.module.UiModules.RegisterModule
import com.application.ui.login.LoginFragment
import com.application.ui.MainActivity
import com.application.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun bindRegisterFragment(): RegisterFragment
}
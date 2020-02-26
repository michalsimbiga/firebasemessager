package com.application.di

import com.application.di.module.uimodules.LoginModule
import com.application.di.module.uimodules.MainModule
import com.application.di.module.uimodules.MessagesModule
import com.application.di.module.uimodules.RegisterModule
import com.application.ui.login.LoginFragment
import com.application.ui.MainActivity
import com.application.ui.messages.MessagesFragment
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

    @ContributesAndroidInjector(modules = [MessagesModule::class])
    abstract fun bindMessagesFragment(): MessagesFragment
}
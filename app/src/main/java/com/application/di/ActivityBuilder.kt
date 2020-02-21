package com.application.di

import com.application.di.module.mainActivity.MainActivityModule
import com.application.di.module.mainActivity.MainModule
import com.application.ui.LoginFragment
import com.application.ui.MainActivity
import com.application.ui.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            MainModule::class]
    )
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment
}
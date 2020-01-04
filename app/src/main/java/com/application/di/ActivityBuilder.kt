package com.application.di

import com.application.di.module.mainActivity.MainActivityModule
import com.application.di.module.mainActivity.MainModule
import com.application.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        MainModule::class])
    abstract fun bindMainActivity(): MainActivity
}
package com.application.di

import android.app.Application
import com.application.MyApplication
import com.application.di.module.AppModule
import com.application.di.module.NetworkModule
import com.application.di.module.RepositoryModule
import com.application.di.module.ViewModelAssistedFactoriesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelAssistedFactoriesModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun bindingComponentBuilder(): BindingComponent.Builder
}
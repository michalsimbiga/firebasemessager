package com.application.di

import androidx.databinding.DataBindingComponent
import com.application.di.module.BindingModule
import com.application.di.scope.DataBindingScoped
import dagger.Subcomponent

@DataBindingScoped
@Subcomponent(modules = [BindingModule::class])
interface BindingComponent: DataBindingComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): BindingComponent
    }
}
package com.application.di.module

import com.application.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.application.repository.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindCreateUserWithEmailAndPasswordUseCase(authRepo: AuthenticationRepositoryImpl): CreateUserWithEmailAndPasswordUseCase
}
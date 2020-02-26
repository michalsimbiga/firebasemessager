package com.application.di.module

import com.application.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.application.repository.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun bindCreateUserWithEmailAndPasswordUseCase(authRepo: AuthenticationRepositoryImpl):
            CreateUserWithEmailAndPasswordUseCase = CreateUserWithEmailAndPasswordUseCase(authRepo)
}
package com.application.di.module

import com.application.domain.repository.AuthenticationRepository
import com.application.domain.repository.StorageRepository
import com.application.data.repositories.AuthenticationRepositoryImpl
import com.application.data.repositories.StorageRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthenticationRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    abstract fun bindStorageRepository(repository: StorageRepositoryImpl) : StorageRepository
}
package com.application.di.module

import com.application.domain.abstracts.AuthenticationRepository
import com.application.domain.abstracts.StorageRepository
import com.application.data.repositories.AuthenticationRepositoryImpl
import com.application.data.repositories.StorageRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticationRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindStorageRepository(repository: StorageRepositoryImpl) : StorageRepository
}
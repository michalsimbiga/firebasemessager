package com.application.di.module

import com.application.net.RestApi
import com.application.repository.AuthenticationRepositoryImpl
import com.application.repository.MyRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(restApi: RestApi): MyRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthenticationRepositoryImpl
}
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
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(restApi: RestApi): MyRepository = MyRepository(restApi)

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthenticationRepositoryImpl = AuthenticationRepositoryImpl(firebaseAuth)
}
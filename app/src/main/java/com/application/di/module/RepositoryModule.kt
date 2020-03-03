package com.application.di.module

import com.application.net.RestApi
import com.application.repository.AuthenticationRepositoryImpl
import com.application.repository.MyRepository
import com.application.repository.StorageRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
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
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthenticationRepositoryImpl =
        AuthenticationRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideStorageRepository(
        firebaseStorage: FirebaseStorage,
        firebaseDatabase: FirebaseDatabase,
        authRepo: AuthenticationRepositoryImpl
    ): StorageRepositoryImpl =
        StorageRepositoryImpl(firebaseStorage, firebaseDatabase, authRepo)
}
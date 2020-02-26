package com.application.di.module

import com.application.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.application.domain.usecase.UploadImageToFirebaseStorageUseCase
import com.application.repository.AuthenticationRepositoryImpl
import com.application.repository.StorageRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun bindCreateUserWithEmailAndPasswordUseCase(authRepo: AuthenticationRepositoryImpl):
            CreateUserWithEmailAndPasswordUseCase = CreateUserWithEmailAndPasswordUseCase(authRepo)

    @Provides
    fun bindUploadImageToFirebaseStorageUseCase(storageRepo: StorageRepositoryImpl):
            UploadImageToFirebaseStorageUseCase = UploadImageToFirebaseStorageUseCase(storageRepo)
}
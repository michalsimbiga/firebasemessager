package com.application.di.module

import com.application.domain.usecase.databaseusecases.FetchAllUsersUseCase
import com.application.domain.usecase.authusecases.CreateUserWithEmailAndPasswordUseCase
import com.application.domain.usecase.databaseusecases.SaveUserToFirebaseDatabaseUseCase
import com.application.domain.usecase.databaseusecases.UploadImageToFirebaseStorageUseCase
import com.application.domain.usecase.authusecases.CheckUserSignedInUseCase
import com.application.domain.usecase.authusecases.SignOutUseCase
import com.application.domain.usecase.databaseusecases.SendMessageToUserUseCase
import com.application.repository.AuthenticationRepositoryImpl
import com.application.repository.StorageRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun bindCreateUserWithEmailAndPasswordUseCase(authRepo: AuthenticationRepositoryImpl):
            CreateUserWithEmailAndPasswordUseCase =
        CreateUserWithEmailAndPasswordUseCase(
            authRepo
        )

    @Provides
    fun bindUploadImageToFirebaseStorageUseCase(storageRepo: StorageRepositoryImpl):
            UploadImageToFirebaseStorageUseCase =
        UploadImageToFirebaseStorageUseCase(
            storageRepo
        )

    @Provides
    fun bindSaveUserToFirebaseDatabaseUseCase(
        storageRepo: StorageRepositoryImpl,
        authRepo: AuthenticationRepositoryImpl
    ): SaveUserToFirebaseDatabaseUseCase =
        SaveUserToFirebaseDatabaseUseCase(
            storageRepo,
            authRepo
        )

    @Provides
    fun bindSignOutUseCase(authRepo: AuthenticationRepositoryImpl):
            SignOutUseCase = SignOutUseCase(authRepo)

    @Provides
    fun bindCheckUserSignedInUseCase(authRepo: AuthenticationRepositoryImpl):
            CheckUserSignedInUseCase = CheckUserSignedInUseCase(authRepo)

    @Provides
    fun bindFetchAllUsersUseCase(storageRepo: StorageRepositoryImpl):
            FetchAllUsersUseCase =
        FetchAllUsersUseCase(
            storageRepo
        )

    @Provides
    fun bindSendMessageToUserUseCase(storageRepo: StorageRepositoryImpl):
            SendMessageToUserUseCase = SendMessageToUserUseCase(storageRepo)
}
package com.application.di.module

import com.application.domain.repository.AuthenticationRepository
import com.application.domain.repository.StorageRepository
import com.application.domain.usecase.authusecases.GetCurrentUserUseCase
import com.application.domain.usecase.authusecases.CreateUserWithEmailAndPasswordUseCase
import com.application.domain.usecase.authusecases.LogInWithEmailAndPasswordUseCase
import com.application.domain.usecase.authusecases.SignOutUseCase
import com.application.domain.usecase.databaseusecases.FetchAllUsersUseCase
import com.application.domain.usecase.databaseusecases.SaveUserToFirebaseDatabaseUseCase
import com.application.domain.usecase.databaseusecases.SendMessageToUserUseCase
import com.application.domain.usecase.databaseusecases.UploadImageToFirebaseStorageUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun bindCreateUserWithEmailAndPasswordUseCase(authRepo: AuthenticationRepository): CreateUserWithEmailAndPasswordUseCase =
        CreateUserWithEmailAndPasswordUseCase(authRepo)

    @Provides
    fun bindUploadImageToFirebaseStorageUseCase(storageRepo: StorageRepository): UploadImageToFirebaseStorageUseCase =
        UploadImageToFirebaseStorageUseCase(storageRepo)

    @Provides
    fun bindSaveUserToFirebaseDatabaseUseCase(
        storageRepo: StorageRepository,
        authRepo: AuthenticationRepository
    ): SaveUserToFirebaseDatabaseUseCase =
        SaveUserToFirebaseDatabaseUseCase(storageRepo, authRepo)

    @Provides
    fun bindSignOutUseCase(authRepo: AuthenticationRepository): SignOutUseCase =
        SignOutUseCase(authRepo)

    @Provides
    fun bindCheckUserSignedInUseCase(authRepo: AuthenticationRepository): GetCurrentUserUseCase =
        GetCurrentUserUseCase(authRepo)

    @Provides
    fun bindFetchAllUsersUseCase(storageRepo: StorageRepository): FetchAllUsersUseCase =
        FetchAllUsersUseCase(storageRepo)

    @Provides
    fun bindSendMessageToUserUseCase(storageRepo: StorageRepository): SendMessageToUserUseCase =
        SendMessageToUserUseCase(storageRepo)

    @Provides
    fun bindLogInWithEmailAndPasswordUseCase(authRepo: AuthenticationRepository): LogInWithEmailAndPasswordUseCase =
        LogInWithEmailAndPasswordUseCase(authRepo)
}
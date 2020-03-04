package com.application.di.module

import com.application.domain.usecase.databaseusecases.FetchAllUsersUseCase
import com.application.domain.usecase.authusecases.CreateUserWithEmailAndPasswordUseCase
import com.application.domain.usecase.databaseusecases.SaveUserToFirebaseDatabaseUseCase
import com.application.domain.usecase.databaseusecases.UploadImageToFirebaseStorageUseCase
import com.application.domain.usecase.authusecases.CheckUserSignedInUseCase
import com.application.domain.usecase.authusecases.SignOutUseCase
import com.application.domain.usecase.databaseusecases.SendMessageToUserUseCase
import com.application.data.repositories.AuthenticationRepositoryImpl
import com.application.data.repositories.StorageRepositoryImpl
import com.application.domain.abstracts.AuthenticationRepository
import com.application.domain.abstracts.StorageRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun bindCreateUserWithEmailAndPasswordUseCase(authRepo: AuthenticationRepository):
            CreateUserWithEmailAndPasswordUseCase = CreateUserWithEmailAndPasswordUseCase(authRepo)

    @Provides
    fun bindUploadImageToFirebaseStorageUseCase(storageRepo: StorageRepository):
            UploadImageToFirebaseStorageUseCase = UploadImageToFirebaseStorageUseCase(storageRepo)

    @Provides
    fun bindSaveUserToFirebaseDatabaseUseCase(
        storageRepo: StorageRepository,
        authRepo: AuthenticationRepository
    ): SaveUserToFirebaseDatabaseUseCase = SaveUserToFirebaseDatabaseUseCase(storageRepo, authRepo)

    @Provides
    fun bindSignOutUseCase(authRepo: AuthenticationRepository):
            SignOutUseCase = SignOutUseCase(authRepo)

    @Provides
    fun bindCheckUserSignedInUseCase(authRepo: AuthenticationRepository): CheckUserSignedInUseCase =
        CheckUserSignedInUseCase(authRepo)

    @Provides
    fun bindFetchAllUsersUseCase(storageRepo: StorageRepository): FetchAllUsersUseCase =
        FetchAllUsersUseCase(storageRepo)

    @Provides
    fun bindSendMessageToUserUseCase(storageRepo: StorageRepository): SendMessageToUserUseCase =
        SendMessageToUserUseCase(storageRepo)
}
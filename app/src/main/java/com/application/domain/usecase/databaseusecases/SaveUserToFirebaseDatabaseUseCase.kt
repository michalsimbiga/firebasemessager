package com.application.domain.usecase.databaseusecases

import com.application.domain.common.useCase.UseCase
import com.application.extensions.empty
import com.application.net.MyResult
import com.application.repository.AuthenticationRepositoryImpl
import com.application.repository.StorageRepositoryImpl

class SaveUserToFirebaseDatabaseUseCase(
    private val storageRepo: StorageRepositoryImpl,
    private val authRepo: AuthenticationRepositoryImpl
) : UseCase<Any, SaveUserToFirebaseDatabaseUseCase.Params>() {

    override suspend fun run(params: Params): MyResult<Any> =
        storageRepo.saveUserToFirebaseDatabase(
            authRepo.getAuthenticatedUserUid() ?: String.empty,
            params.username, params.email, params.photoUrl
        )

    data class Params(
        val username: String,
        val email: String,
        val photoUrl: String
    )
}
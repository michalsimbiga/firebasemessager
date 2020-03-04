package com.application.domain.usecase.databaseusecases

import com.application.domain.abstracts.AuthenticationRepository
import com.application.domain.abstracts.StorageRepository
import com.application.domain.common.useCase.UseCase
import com.application.domain.extensions.empty
import com.application.domain.net.MyResult

class SaveUserToFirebaseDatabaseUseCase(
    private val storageRepo: StorageRepository,
    private val authRepo: AuthenticationRepository
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
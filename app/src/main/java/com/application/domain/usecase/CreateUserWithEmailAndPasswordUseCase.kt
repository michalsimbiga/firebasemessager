package com.application.domain.usecase

import com.application.domain.common.useCase.UseCase
import com.application.net.MyResult
import com.application.repository.AuthenticationRepositoryImpl

class CreateUserWithEmailAndPasswordUseCase(
    private val authRepo: AuthenticationRepositoryImpl
): UseCase<Unit, CreateUserWithEmailAndPasswordUseCase.Params>(){

    override suspend fun run(params: Params): MyResult<Unit> =
        authRepo.createUserWithEmailAndPassword(params.email, params.password)

    data class Params(
        val email: String,
        val password: String
    )
}

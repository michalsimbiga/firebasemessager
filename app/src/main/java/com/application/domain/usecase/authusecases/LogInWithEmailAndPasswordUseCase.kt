package com.application.domain.usecase.authusecases

import com.application.domain.common.useCase.UseCase
import com.application.domain.net.MyResult
import com.application.domain.repository.AuthenticationRepository

class LogInWithEmailAndPasswordUseCase(
    private val authRepo: AuthenticationRepository
) : UseCase<Any, LogInWithEmailAndPasswordUseCase.Params>() {

    override suspend fun run(params: Params): MyResult<Any> =
        authRepo.logInWithEmailAndPassword(params.email, params.password)

    data class Params(
        val email: String,
        val password: String
    )
}
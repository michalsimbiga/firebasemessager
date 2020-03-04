package com.application.domain.usecase.authusecases

import com.application.domain.repository.AuthenticationRepository
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.domain.net.MyResult
import com.application.domain.net.success

class SignOutUseCase(private val authRepo: AuthenticationRepository) :
    NoParametersUseCase<Unit>() {

    override suspend fun run(): MyResult<Unit> {
        return success(authRepo.signOut())
    }
}
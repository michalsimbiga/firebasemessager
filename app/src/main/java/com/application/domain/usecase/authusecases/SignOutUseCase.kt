package com.application.domain.usecase.authusecases

import com.application.domain.common.useCase.NoParametersUseCase
import com.application.net.MyResult
import com.application.net.success
import com.application.repository.AuthenticationRepositoryImpl

class SignOutUseCase(private val authRepo: AuthenticationRepositoryImpl) :
    NoParametersUseCase<Unit>() {

    override suspend fun run(): MyResult<Unit> {
        return success(authRepo.signOut())
    }
}
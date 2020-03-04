package com.application.domain.usecase.authusecases

import com.application.domain.common.useCase.NoParametersUseCase
import com.application.net.MyResult
import com.application.net.success
import com.application.repository.AuthenticationRepositoryImpl

class CheckUserSignedInUseCase(private val authRepo: AuthenticationRepositoryImpl): NoParametersUseCase<Boolean>() {
    override suspend fun run(): MyResult<Boolean> {
        val currentUser = authRepo.getCurrentUser()
        return if(currentUser == null) success(false) else success(true)
    }
}
package com.application.domain.usecase.authusecases

import com.application.domain.abstracts.AuthenticationRepository
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.domain.net.MyResult
import com.application.domain.net.success

class CheckUserSignedInUseCase(private val authRepo: AuthenticationRepository): NoParametersUseCase<Boolean>() {
    override suspend fun run(): MyResult<Boolean> {
        val currentUser = authRepo.getCurrentUser()
        return if(currentUser == null) success(false) else success(true)
    }
}
package com.application.domain.usecase.authusecases

import com.application.domain.repository.AuthenticationRepository
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.domain.errorHandling.MyError
import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.success
import com.google.firebase.auth.FirebaseUser

class GetCurrentUserUseCase(private val authRepo: AuthenticationRepository) :
    NoParametersUseCase<FirebaseUser>() {
    override suspend fun run(): MyResult<FirebaseUser> = authRepo.getCurrentUser()
}
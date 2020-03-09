package com.application.domain.usecase.authusecases

import com.application.data.model.User
import com.application.domain.repository.AuthenticationRepository
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.domain.errorHandling.MyError
import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.success
import com.google.firebase.auth.FirebaseUser

class GetCurrentUserUseCase(private val authRepo: AuthenticationRepository) :
    NoParametersUseCase<User>() {
    override suspend fun run(): MyResult<User> = authRepo.getCurrentUser()
}
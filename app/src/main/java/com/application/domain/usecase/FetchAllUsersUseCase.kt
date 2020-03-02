package com.application.domain.usecase

import com.application.domain.common.useCase.NoParametersUseCase
import com.application.model.User
import com.application.net.MyResult
import com.application.net.success
import com.application.repository.StorageRepositoryImpl

class FetchAllUsersUseCase(private val storageRepositoryImpl: StorageRepositoryImpl) :
    NoParametersUseCase<List<User>>() {
    override suspend fun run(): MyResult<List<User>> =
        success(storageRepositoryImpl.fetchAllUsers())
}
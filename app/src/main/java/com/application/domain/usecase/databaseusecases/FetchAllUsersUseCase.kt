package com.application.domain.usecase.databaseusecases

import com.application.data.model.User
import com.application.domain.repository.StorageRepository
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.domain.net.MyResult
import com.application.domain.net.success

class FetchAllUsersUseCase(private val storageRepositoryImpl: StorageRepository) :
    NoParametersUseCase<List<User>>() {
    override suspend fun run(): MyResult<List<User>> =
        success(storageRepositoryImpl.fetchAllUsers())
}
package com.application.domain.usecase.databaseusecases

import com.application.domain.common.useCase.UseCase
import com.application.net.MyResult
import com.application.repository.StorageRepositoryImpl

class SendMessageToUserUseCase(
    private val storageRepo: StorageRepositoryImpl
) : UseCase<Any, SendMessageToUserUseCase.Params>() {

    override suspend fun run(params: Params): MyResult<Boolean> =
        storageRepo.sendMessageToUser(params.receipientUid, params.message)

    data class Params(
        val receipientUid: String,
        val message: String
    )
}
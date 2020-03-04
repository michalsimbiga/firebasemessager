package com.application.domain.usecase.databaseusecases

import android.net.Uri
import com.application.domain.abstracts.StorageRepository
import com.application.domain.common.useCase.UseCase
import com.application.domain.net.MyResult

class UploadImageToFirebaseStorageUseCase(
    private val storageRepo: StorageRepository
) : UseCase<String, UploadImageToFirebaseStorageUseCase.Params>() {

    override suspend fun run(params: Params): MyResult<String> =
        storageRepo.uploadImageToFirebaseStorage(params.imageUri)

    data class Params(
        val imageUri: Uri
    )
}
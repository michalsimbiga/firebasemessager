package com.application.domain.usecase

import android.net.Uri
import com.application.domain.common.useCase.UseCase
import com.application.net.MyResult
import com.application.repository.StorageRepositoryImpl

class UploadImageToFirebaseStorageUseCase(
    private val storageRepo: StorageRepositoryImpl
) : UseCase<String, UploadImageToFirebaseStorageUseCase.Params>() {

    override suspend fun run(params: Params): MyResult<String> =
        storageRepo.uploadImageToFirebaseStorage(params.imageUri)

    data class Params(
        val imageUri: Uri
    )
}
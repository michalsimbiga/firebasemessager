package com.application.domain.repository

import android.net.Uri
import com.application.data.model.User
import com.application.domain.net.MyResult

interface StorageRepository {

    suspend fun uploadImageToFirebaseStorage(imageUri: Uri): MyResult<String>

    suspend fun saveUserToFirebaseDatabase(
        userUid: String,
        username: String,
        email: String,
        photoUrl: String
    ): MyResult<String>

    suspend fun fetchAllUsers(): List<User>

    suspend fun sendMessageToUser(receipientUid: String, message: String): MyResult<Boolean>
}
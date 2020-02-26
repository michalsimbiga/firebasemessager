package com.application.repository

import android.net.Uri
import com.application.net.MyResult
import com.application.net.failure
import com.application.net.success
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.coroutines.resume

class StorageRepositoryImpl(private val firebaseStorage: FirebaseStorage) {

    suspend fun uploadImageToFirebaseStorage(imageUri: Uri) =
        suspendCancellableCoroutine<MyResult<String>> { coroutine ->
            val filename = UUID.randomUUID().toString()
            val storageRef = firebaseStorage.getReference("/images/$filename")

            storageRef.putFile(imageUri)
                .addOnCompleteListener { storagePutTask ->
                    if (storagePutTask.isSuccessful) {
                        storageRef.downloadUrl
                            .addOnCompleteListener { urlRetrieveTask ->
                                if (urlRetrieveTask.isSuccessful) coroutine.resume(
                                    success(
                                        urlRetrieveTask.result.toString()
                                    )
                                )
                                else coroutine.resume(failure(urlRetrieveTask.exception!!))
                            }
                    } else coroutine.resume(failure(storagePutTask.exception!!))
                }
        }

}
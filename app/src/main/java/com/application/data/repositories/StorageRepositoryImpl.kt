package com.application.data.repositories

import android.net.Uri
import com.application.data.model.Message
import com.application.data.model.User
import com.application.domain.repository.AuthenticationRepository
import com.application.domain.repository.StorageRepository
import com.application.domain.extensions.empty
import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.success
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class StorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
    private val firebaseDatabase: FirebaseDatabase,
    private val authRepo: AuthenticationRepository
) : StorageRepository {

    override suspend fun uploadImageToFirebaseStorage(imageUri: Uri) =
        suspendCancellableCoroutine<MyResult<String>> { coroutine ->
            val filename = UUID.randomUUID().toString()
            val storageRef = firebaseStorage.getReference("/images/$filename")

            storageRef.putFile(imageUri)
                .addOnCompleteListener { storagePutTask ->
                    if (storagePutTask.isSuccessful) {
                        storageRef.downloadUrl
                            .addOnCompleteListener { urlRetrieveTask ->
                                if (urlRetrieveTask.isSuccessful) coroutine.resume(
                                    success(urlRetrieveTask.result.toString())
                                )
                                else coroutine.resume(failure(urlRetrieveTask.exception!!))
                            }
                            .addOnCanceledListener { coroutine.cancel() }
                    } else coroutine.resume(failure(storagePutTask.exception!!))
                }
                .addOnCanceledListener { coroutine.cancel() }
        }

    override suspend fun saveUserToFirebaseDatabase(
        userUid: String, username: String, email: String, photoUrl: String
    ) = suspendCancellableCoroutine<MyResult<String>> { coroutine ->
        val dbReference = firebaseDatabase.getReference("/users/${userUid}}")

        dbReference.setValue(prepareUser(username, email, photoUrl))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) coroutine.resume(success(task.result.toString()))
                else coroutine.resume(failure(task.exception!!))
            }
            .addOnCanceledListener { coroutine.cancel() }
    }

    override suspend fun fetchAllUsers() = suspendCancellableCoroutine<List<User>> { coroutine ->
        val ref = firebaseDatabase.getReference("/users")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                coroutine.cancel()
            }

            override fun onDataChange(p0: DataSnapshot) {
                val retList = mutableListOf<User>()
                p0.children.forEach {
                    it.getValue(User::class.java).apply { if (this != null) retList.add(this) }
                }
                coroutine.resume(retList.toList())
            }

        })
    }

    override suspend fun sendMessageToUser(receipientUid: String, message: String) =
        suspendCancellableCoroutine<MyResult<Boolean>> { coroutine ->
            val reference = firebaseDatabase.getReference("/messages").push()

            val fromId = authRepo.getAuthenticatedUserUid() ?: String.empty

            val prepedMessage = prepareMessage(
                reference.key ?: String.empty,
                message, fromId, receipientUid, System.currentTimeMillis() / 1000
            )

            reference.setValue(prepedMessage)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) coroutine.resume(success(true))
                    else coroutine.resume(failure(task.exception!!))
                }
                .addOnCanceledListener { coroutine.cancel() }

        }

    private fun prepareMessage(
        id: String, message: String, fromId: String, toId: String, timestamp: Long
    ) = Message(id, message, fromId, toId, timestamp)

    private fun prepareUser(username: String, email: String, photoUrl: String) =
        User(
            authRepo.getAuthenticatedUserUid() ?: String.empty,
            username,
            email,
            photoUrl
        )
}

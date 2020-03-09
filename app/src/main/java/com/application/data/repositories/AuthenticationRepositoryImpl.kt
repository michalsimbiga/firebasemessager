package com.application.data.repositories

import com.application.data.model.User
import com.application.domain.errorHandling.MyError
import com.application.domain.repository.AuthenticationRepository
import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.success
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) :
    AuthenticationRepository {

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        suspendCancellableCoroutine<MyResult<Unit>> { coroutine ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) coroutine.resume(success(Unit))
                    else coroutine.resume(failure(it.exception!!))
                }
                .addOnCanceledListener { coroutine.cancel() }
        }

    override fun getAuthenticatedUserUid() = firebaseAuth.uid

    override suspend fun getCurrentUser() =
        suspendCancellableCoroutine<MyResult<User>> { coroutine ->
            val user = firebaseAuth.currentUser
            val ref = firebaseDatabase.getReference("/users/${user?.uid}}")

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    coroutine.cancel()
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val foundUser = p0.getValue(User::class.java) ?: User()
                    coroutine.resume(success(foundUser))
                }
            })
        }

    override fun signOut() = firebaseAuth.signOut()
}
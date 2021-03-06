package com.application.data.repositories

import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.success
import com.application.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationRepository {

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        suspendCancellableCoroutine<MyResult<Unit>> { coroutine ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) coroutine.resume(success(Unit))
                    else coroutine.resume(failure(it.exception!!))
                }
                .addOnCanceledListener { coroutine.cancel() }
        }

    override suspend fun logInWithEmailAndPassword(
        email: String,
        password: String
    ): MyResult<Unit> = suspendCancellableCoroutine<MyResult<Unit>> { coroutine ->
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) coroutine.resume(success(Unit))
                else coroutine.resume(failure(it.exception!!))
            }
    }

    override fun getAuthenticatedUserUid() = firebaseAuth.uid
    override fun getCurrentUser() = firebaseAuth.currentUser

    override fun signOut() = firebaseAuth.signOut()
}
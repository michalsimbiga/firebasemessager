package com.application.repository

import com.application.domain.abstracts.AuthenticationRepository
import com.application.net.MyResult
import com.application.net.failure
import com.application.net.success
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

class AuthenticationRepositoryImpl(private val firebaseAuth: FirebaseAuth) :
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

    override fun getCurrentUser() = firebaseAuth.currentUser

    override fun signOut() = firebaseAuth.signOut()
}
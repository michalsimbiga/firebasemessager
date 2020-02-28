package com.application.repository

import com.application.net.MyResult
import com.application.net.failure
import com.application.net.success
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

class AuthenticationRepositoryImpl(private val firebaseAuth: FirebaseAuth) {

    suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        suspendCancellableCoroutine<MyResult<Unit>> { coroutine ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) coroutine.resume(success(Unit))
                    else coroutine.resume(failure(it.exception!!))
                }
                .addOnCanceledListener { coroutine.cancel() }
        }

    fun getAuthenticatedUserUid() = firebaseAuth.uid

    fun checkCurrentUser() =
        firebaseAuth.currentUser.also { Timber.i("TESTING checkCurrentUser $it") }

    fun signOut() = firebaseAuth.signOut()
}
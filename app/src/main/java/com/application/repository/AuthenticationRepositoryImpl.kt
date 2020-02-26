package com.application.repository

import com.application.net.MyResult
import com.application.net.failure
import com.application.net.success
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AuthenticationRepositoryImpl(private val firebaseAuth: FirebaseAuth) {

    suspend fun createUserWithEmailAndPassword(email: String, password: String) =
        suspendCancellableCoroutine<MyResult<Unit>> { coroutine ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) coroutine.resume(success(Unit))
                    else coroutine.resume(failure(it.exception!!))
                }
        }

    fun getAuthenticatedUserUid() = firebaseAuth.uid
}
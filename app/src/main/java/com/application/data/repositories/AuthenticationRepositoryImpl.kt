package com.application.data.repositories

import com.application.domain.errorHandling.MyError
import com.application.domain.repository.AuthenticationRepository
import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.success
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
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

    override fun getCurrentUser(): MyResult<FirebaseUser> {
        val user = firebaseAuth.currentUser
        return if (user == null) failure(Exception("User not signed in")) else success(user)
    }

    override fun signOut() = firebaseAuth.signOut()
}
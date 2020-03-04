package com.application.domain.abstracts

import com.application.net.MyResult
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): MyResult<Unit>

    fun getAuthenticatedUserUid(): String?
    fun getCurrentUser(): FirebaseUser?
    fun signOut()
}
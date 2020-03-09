package com.application.domain.repository

import com.application.domain.net.MyResult
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): MyResult<Unit>

    fun getAuthenticatedUserUid(): String?
    fun getCurrentUser(): MyResult<FirebaseUser>
    fun signOut()
}
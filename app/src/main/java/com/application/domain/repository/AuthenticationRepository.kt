package com.application.domain.repository

import com.application.data.model.User
import com.application.domain.net.MyResult
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): MyResult<Unit>
    suspend fun getCurrentUser(): MyResult<User>

    fun getAuthenticatedUserUid(): String?
    fun signOut()
}
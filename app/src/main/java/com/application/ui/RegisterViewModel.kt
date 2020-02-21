package com.application.ui

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.extensions.empty
import com.application.net.MyResult
import com.application.net.success
import com.application.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import timber.log.Timber
import java.util.*

class RegisterViewModel(private val firebaseAuth: FirebaseAuth) : BaseViewModel() {

    var username: String = String.empty
    var password: String = String.empty
    var email: String = String.empty

    var photoUri: Uri? = null
    var photoUrl: String? = null

    private val _response = MutableLiveData<MyResult<*>>()
    val responseLiveData: LiveData<MyResult<*>> = _response

    fun register() {
        Timber.i("TESTING username: $username password: $password")
        if (email.isEmpty() or password.isEmpty()) return

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { uploadImageToFirebaseStorage() }
    }

    fun uploadImageToFirebaseStorage() {
        if (photoUri != null) {

            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            ref.putFile(photoUri!!)
                .addOnSuccessListener {
                    photoUrl = it.metadata?.path
                    Timber.i("TESTING photo ${it.metadata}")
                    Timber.i("TESTING photo ${it.metadata?.path}")
                    Timber.i("TESTING photo ${it.metadata?.bucket}")
                    saveUserToFirebaseDatabase()
                }
        }
    }

    fun saveUserToFirebaseDatabase() {
        val ref = FirebaseDatabase.getInstance().getReference("/users/${firebaseAuth.uid}")
        ref.setValue(User(username, email, photoUrl!!))
            .addOnSuccessListener {
                _response.postValue(success(Unit))
            }

    }
}

data class User(
    val username: String,
    val email: String,
    val profileImage: String
)
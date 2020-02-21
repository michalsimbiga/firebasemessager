package com.application.ui

import androidx.lifecycle.ViewModel
import com.application.extensions.empty
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class RegisterViewModel: ViewModel() {

    var username: String = String.empty
    var password: String = String.empty
    var email: String = String.empty

    fun register(){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { Timber.i("TESTING complete $it") }
            .addOnSuccessListener {  Timber.i("TESTING success $it") }
            .addOnFailureListener {  Timber.i("TESTING failure $it") }
    }
}
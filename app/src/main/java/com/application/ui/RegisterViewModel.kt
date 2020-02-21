package com.application.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.extensions.empty
import com.application.net.MyResult
import com.application.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class RegisterViewModel(private val firebaseAuth: FirebaseAuth) : BaseViewModel() {

    var username: String = String.empty
    var password: String = String.empty
    var email: String = String.empty

    private val _response = MutableLiveData<MyResult<*>>()
    val responseLiveData : LiveData<MyResult<*>> = _response

    fun register() {
        Timber.i("TESTING username: $username password: $password")
        if(email.isEmpty() or password.isEmpty()) return
        executeFirebase(
            receiver = { result -> _response.value = result},
            request = { firebaseAuth.createUserWithEmailAndPassword(email, password)}
        )
    }
}
package com.application.ui.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.extensions.delegate
import com.application.extensions.empty
import com.application.model.User
import com.application.net.MyResult
import com.application.net.failure
import com.application.net.success
import com.application.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber
import java.util.*

class RegisterViewModel @AssistedInject constructor(
    private val firebaseAuth: FirebaseAuth,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<RegisterViewModel>

    var username: String by stateHandle.delegate(String.empty)
    var password: String by stateHandle.delegate(String.empty)
    var email: String by stateHandle.delegate(String.empty)

    var photoUri: Uri? = null
    var photoUrl: String? = null

    private val _response = MutableLiveData<MyResult<*>>()
    val responseLiveData: LiveData<MyResult<*>> = _response

    private fun setResponseFailure(message: String?) {
        _response.value = failure(Exception(message))
    }

    fun register() {
        if (email.isEmpty() or password.isEmpty()) return

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) uploadImageToFirebaseStorage()
                else setResponseFailure(it.exception?.localizedMessage)
            }

//        executeFirebase(
//            receiver = { response: MyResult<*> -> _response.value = response },
//            request = { firebaseAuth.createUserWithEmailAndPassword(email, password) }
//        )
    }

//    fun <T : Any> MyResult<T>.doOn(
//        successCallback: () -> Unit,
//        failureCallback: () -> Unit
//    ) {
//        when(this) {
//            is MyResult.Success -> successCallback()
//            is MyResult.Failure -> failureCallback()
//        }
//    }

    fun uploadImageToFirebaseStorage() {
        if (photoUri != null) {

            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            ref.putFile(photoUri!!)
                .addOnCompleteListener {
                    Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ")
                    Timber.i("TESTING result ${it.result.toString()} ")
                }
                .addOnFailureListener { setResponseFailure(it.localizedMessage) }
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        photoUrl = uri.toString()
                        Timber.i("TESTING photoUrl $photoUrl")
                        saveUserToFirebaseDatabase()
                    }
                }
                .addOnCompleteListener { Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ") }
                .addOnFailureListener { setResponseFailure(it.localizedMessage) }

//                    ref.downloadUrl.addOnSuccessListener {
//                        photoUrl = it.toString()
//                        Timber.i("TESTING photoUrl $photoUrl")
//                        saveUserToFirebaseDatabase()
//                    }
//                        .addOnCompleteListener { Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ") }
//                        .addOnFailureListener { setResponseFailure(it.message) }
        }
    }

    fun saveUserToFirebaseDatabase() {
        val ref = FirebaseDatabase.getInstance().getReference("/users/${firebaseAuth.uid}")
        ref.setValue(User(username, email, photoUrl!!))
            .addOnSuccessListener { _response.postValue(success(Unit)) }
            .addOnCompleteListener { Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ") }
            .addOnFailureListener { setResponseFailure(it.message) }
    }
}
package com.application.ui

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.extensions.delegate
import com.application.extensions.empty
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
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RegisterViewModel @AssistedInject constructor(
    private val firebaseAuth: FirebaseAuth,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<RegisterViewModel>

//    inner class SavedStateDelegate(private val defaultVal: String) {
//        operator fun getValue(thisRef: Any, property: KProperty<*>): String {
////            val stateKey = property.name
//            return stateHandle.get(property.name) ?: defaultVal
//        }
//
//        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
//            stateHandle.set(property.name, value)
//        }
//    }

    var username: String by stateHandle.delegate(String.empty)
    var password: String by stateHandle.delegate(String.empty)
    var email: String by stateHandle.delegate(String.empty)

    var photoUri: Uri? = null
    var photoUrl: String? = null

    init {
        Timber.i("TESTING login view model saved state : $stateHandle ${stateHandle.keys()}")
    }

    private val _response = MutableLiveData<MyResult<*>>()
    val responseLiveData: LiveData<MyResult<*>> = _response

    private fun setResponseFailure(message: String?) {
        _response.value = failure(Exception(), message ?: String.empty)
    }

    fun register() {
        Timber.i("TESTING loginUsername: $username loginPassword: $password")
        if (email.isEmpty() or password.isEmpty()) return

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { uploadImageToFirebaseStorage() }
            .addOnCompleteListener {
                Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ")
                Timber.i("TESTING result ${it.result.toString()}")
            }
            .addOnFailureListener { setResponseFailure(it.message) }
    }

    fun uploadImageToFirebaseStorage() {
        if (photoUri != null) {

            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            ref.putFile(photoUri!!)
                .addOnCompleteListener {
                    Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ")
                    Timber.i("TESTING result ${it.result.toString()} ")
                }
                .addOnFailureListener { setResponseFailure(it.message) }
                .addOnSuccessListener {
                    photoUrl = it.uploadSessionUri.toString()
                    saveUserToFirebaseDatabase()
                }
                .addOnCompleteListener { Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ") }
                .addOnFailureListener { setResponseFailure(it.message) }

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
            .addOnSuccessListener {
                _response.postValue(success(Unit))
            }
            .addOnCompleteListener { Timber.i("TESTING onComplete ${it.javaClass.enclosingMethod?.name} ") }
            .addOnFailureListener { setResponseFailure(it.message) }
    }
}

data class User(
    val username: String,
    val email: String,
    val profileImage: String
)
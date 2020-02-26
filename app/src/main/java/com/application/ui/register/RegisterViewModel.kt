package com.application.ui.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.CreateUserWithEmailAndPasswordUseCase
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
import com.google.firebase.storage.StorageReference
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber
import java.util.*

class RegisterViewModel @AssistedInject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val createUserWithEmailAndPasswordUseCase: CreateUserWithEmailAndPasswordUseCase,
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
        _response.value = MyResult.Loading(false)
        _response.value = failure(Exception(message))
    }

    fun register() {
        Timber.i("TESTING register ")

        if (email.isEmpty() or password.isEmpty()) return

        _response.value = MyResult.Loading(true)

        createUserWithEmailAndPasswordUseCase.execute(
            params = CreateUserWithEmailAndPasswordUseCase.Params(email, password),
            stateReducer = { result ->
                when (result) {
                    is MyResult.Success -> uploadImageToFirebaseStorage()
                    is MyResult.Failure -> setResponseFailure(result.message)
                }
            }
        )
    }

    private fun uploadImageToFirebaseStorage() {
        Timber.i("TESTING uploadImageToFirebaseStorage")
        if (photoUri != null) {

            Timber.i("TESTING uploadImageToFirebaseStorage ok")

            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            executeFirebase(
                onSuccessReceiver = { retrieveImageUrl(ref) },
                onFailureReceiver = { exception -> setResponseFailure(exception.localizedMessage) },
                request = { ref.putFile(photoUri!!) }
            )
        }
    }

    private fun retrieveImageUrl(storageReference: StorageReference) {
        Timber.i("TESTING retrieveImageUrl ")
        executeFirebase(
            onSuccessReceiver = { uri ->
                Timber.i("TESTING retrieveImageUlr success ${uri}")
                photoUrl = uri.toString()
                saveUserToFirebaseDatabase()
            },
            onFailureReceiver = { exception -> setResponseFailure(exception.localizedMessage) },
            request = { storageReference.downloadUrl }
        )
    }

    private fun saveUserToFirebaseDatabase() {
        Timber.i("TESTING saveUserToFirebaseDatabase")
        val dbReference = FirebaseDatabase.getInstance().getReference("/users/${firebaseAuth.uid}")

        executeFirebase(
            onSuccessReceiver = {
                _response.value = MyResult.Loading(false)
                _response.value = success(Unit)
            },
            onFailureReceiver = { exception -> setResponseFailure(exception.localizedMessage) },
            request = { dbReference.setValue(User(username, email, photoUrl!!)) }
        )
    }
}
package com.application.ui.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.application.domain.usecase.SaveUserToFirebaseDatabaseUseCase
import com.application.domain.usecase.UploadImageToFirebaseStorageUseCase
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
    private val uploadImageToFirebaseStorageUseCase: UploadImageToFirebaseStorageUseCase,
    private val saveUserToFirebaseDatabaseUseCase: SaveUserToFirebaseDatabaseUseCase,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<RegisterViewModel>

    var username: String by stateHandle.delegate(String.empty)
    var password: String by stateHandle.delegate(String.empty)
    var email: String by stateHandle.delegate(String.empty)
    var photoUri: Uri by stateHandle.delegate(Uri.EMPTY)
    var photoUrl: String by stateHandle.delegate(String.empty)

    private val _response = MutableLiveData<MyResult<*>>()
    val responseLiveData: LiveData<MyResult<*>> = _response

    private fun setResponseFailure(message: String?) = with(_response){
        value = MyResult.Loading(false)
        value = failure(Exception(message))
    }

    private fun doOnSuccess(result: MyResult<Any>, callback: () -> Unit) {
        when (result) {
            is MyResult.Success -> callback()
            is MyResult.Failure -> setResponseFailure(result.message)
        }
    }

    fun register() {
         if (email.isEmpty() or password.isEmpty()) return

        _response.value = MyResult.Loading(true)

        createUserWithEmailAndPasswordUseCase.execute(
            params = CreateUserWithEmailAndPasswordUseCase.Params(email, password),
            stateReducer = { result -> doOnSuccess(result) { uploadImageToFirebaseStorage() } }
        )
    }

    private fun uploadImageToFirebaseStorage() {
        uploadImageToFirebaseStorageUseCase.execute(
            params = UploadImageToFirebaseStorageUseCase.Params(photoUri),
            stateReducer = { result ->
                doOnSuccess(result) {
                    photoUrl = result.toString()
                    saveUserToFirebaseDatabase()
                }
            }
        )
    }

    private fun saveUserToFirebaseDatabase() {
        saveUserToFirebaseDatabaseUseCase.execute(
            params = SaveUserToFirebaseDatabaseUseCase.Params(username, email, photoUrl),
            stateReducer = { result -> doOnSuccess(result) { _response.value = success(Unit) } }
        )
    }
}
package com.application.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.data.model.User
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.extensions.delegate
import com.application.domain.extensions.empty
import com.application.domain.net.MyResult
import com.application.domain.usecase.authusecases.GetCurrentUserUseCase
import com.application.domain.usecase.authusecases.LogInWithEmailAndPasswordUseCase
import com.application.presentation.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class LoginViewModel @AssistedInject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logInWithEmailAndPasswordUseCase: LogInWithEmailAndPasswordUseCase,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<LoginViewModel>

    private val _signedInResponse = MutableLiveData<MyResult<User>>()
    val signedInResponse: LiveData<MyResult<User>> = _signedInResponse

    private val _loginResponse = MutableLiveData<MyResult<Any>>()
    val loginResponse: LiveData<MyResult<Any>> = _loginResponse

    var loginEmail: String by stateHandle.delegate(String.empty)
    var loginPassword: String by stateHandle.delegate(String.empty)

    init {
        checkIfSignedIn()
    }

    private fun checkIfSignedIn() {
        getCurrentUserUseCase.execute(
            stateReducer = { response -> _signedInResponse.value = response }
        )
    }

    fun logIn() {
        if (loginEmail.isBlank() or loginPassword.isBlank()) return

        logInWithEmailAndPasswordUseCase.execute(
            params = LogInWithEmailAndPasswordUseCase.Params(loginEmail, loginPassword),
            stateReducer = { result -> _loginResponse.postValue(result) }
        )
    }
}

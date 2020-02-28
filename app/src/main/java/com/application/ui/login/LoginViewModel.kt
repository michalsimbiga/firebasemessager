package com.application.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.authusecases.CheckUserSignedInUseCase
import com.application.extensions.delegate
import com.application.extensions.empty
import com.application.net.MyResult
import com.application.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class LoginViewModel @AssistedInject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val checkUserSignedInUseCase: CheckUserSignedInUseCase,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<LoginViewModel>

    private val _signedInResponse = MutableLiveData<MyResult<Boolean>>()
    val signedInResponse: LiveData<MyResult<Boolean>> = _signedInResponse

    var loginUsername: String by stateHandle.delegate(String.empty)
    var loginPassword: String by stateHandle.delegate(String.empty)

    init {
        checkIfSignedIn()
    }

    private fun checkIfSignedIn() {
        checkUserSignedInUseCase.execute(
            stateReducer = { response -> _signedInResponse.value = response }
        )
    }
}

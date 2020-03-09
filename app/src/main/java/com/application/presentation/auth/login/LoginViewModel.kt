package com.application.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.data.model.User
import com.application.data.model.toUser
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.extensions.delegate
import com.application.domain.extensions.empty
import com.application.domain.net.MyResult
import com.application.domain.usecase.authusecases.GetCurrentUserUseCase
import com.application.presentation.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class LoginViewModel @AssistedInject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<LoginViewModel>

    private val _signedInResponse = MutableLiveData<MyResult<User>>()
    val signedInResponse: LiveData<MyResult<User>> = _signedInResponse

    var loginUsername: String by stateHandle.delegate(String.empty)
    var loginPassword: String by stateHandle.delegate(String.empty)

    init {
        checkIfSignedIn()
    }

    private fun checkIfSignedIn() {
        getCurrentUserUseCase.execute(
            stateReducer = { response -> _signedInResponse.value = response }
        )
    }
}

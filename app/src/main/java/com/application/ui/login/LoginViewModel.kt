package com.application.ui.login

import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.extensions.delegate
import com.application.extensions.empty
import com.application.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import timber.log.Timber

class LoginViewModel @AssistedInject constructor(
    private val firebaseAuth: FirebaseAuth,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<LoginViewModel>

    var loginUsername: String by stateHandle.delegate(String.empty)
    var loginPassword: String by stateHandle.delegate(String.empty)

    init {
        Timber.i("TESTING login view model saved state : $stateHandle ${stateHandle.keys()}")
        Timber.i("TESTING Login View Model loginUsername $loginUsername")
        Timber.i("TESTING Login View Model loginPassword $loginPassword")
    }
}

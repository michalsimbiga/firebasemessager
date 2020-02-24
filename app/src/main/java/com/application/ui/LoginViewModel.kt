package com.application.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.application.di.module.ViewModelAssistedFactory
import com.application.extensions.empty
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class LoginViewModel @AssistedInject constructor(@Assisted stateHandle: SavedStateHandle) :
    ViewModel() {

    var username: String = String.empty
    var password: String = String.empty


    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<LoginViewModel>
}
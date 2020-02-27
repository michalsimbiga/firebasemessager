package com.application.ui.messages

import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.repository.AuthenticationRepositoryImpl
import com.application.ui.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MessagesViewModel @AssistedInject constructor(
    private val authRepo: AuthenticationRepositoryImpl,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MessagesViewModel>

    fun signOut() = authRepo.signOut()
}
package com.application.ui.messages.chat

import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.ui.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ChatViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle
): BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<ChatViewModel>
//
//    private val _usersDataResponse = MutableLiveData<MyResult<List<User>>>()
//    val userDataResponse: LiveData<MyResult<List<User>>> = _usersDataResponse
//
//    init {
//        fetchUsers()
//    }
//
//    private fun fetchUsers() {
//        fetchAllUsersUseCase.execute(
//            stateReducer = { response -> _usersDataResponse.postValue(response) }
//        )
//    }
}
package com.application.ui.messages.chat

import androidx.lifecycle.SavedStateHandle
import com.application.data.model.User
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.databaseusecases.SendMessageToUserUseCase
import com.application.extensions.delegate
import com.application.ui.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ChatViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle,
    private val sendMessageToUserUseCase: SendMessageToUserUseCase
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<ChatViewModel>

    private var receipient: User by stateHandle.delegate(User())

    fun setNewReceipient(user: User){ receipient.copy(
        uid = user.uid,
        username = user.username,
        email = user.email,
        profileImage = user.profileImage
    )}

    fun sendMessage(message: String) {
        sendMessageToUserUseCase.execute(
            params = SendMessageToUserUseCase.Params(receipient.uid, message),
            stateReducer = {}
        )
    }

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
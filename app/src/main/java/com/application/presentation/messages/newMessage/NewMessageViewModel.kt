package com.application.presentation.messages.newMessage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.databaseusecases.FetchAllUsersUseCase
import com.application.data.model.User
import com.application.domain.net.MyResult
import com.application.presentation.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class NewMessageViewModel @AssistedInject constructor(
    private val fetchAllUsersUseCase: FetchAllUsersUseCase,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<NewMessageViewModel>

    private val _usersDataResponse = MutableLiveData<MyResult<List<User>>>()
    val userDataResponse: LiveData<MyResult<List<User>>> = _usersDataResponse

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        fetchAllUsersUseCase.execute(
            stateReducer = { response -> _usersDataResponse.postValue(response) }
        )
    }
}
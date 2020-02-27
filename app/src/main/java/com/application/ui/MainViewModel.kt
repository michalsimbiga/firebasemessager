package com.application.ui

import androidx.lifecycle.*
import com.application.di.module.ViewModelAssistedFactory
import com.application.domain.usecase.authusecases.CheckUserSignedInUseCase
import com.application.net.MyResult
import com.application.repository.MyRepository
import com.application.ui.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MainViewModel @AssistedInject constructor(
    private val checkUserSignedInUseCase: CheckUserSignedInUseCase,
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    private val _response = MutableLiveData<MyResult<Boolean>>()
    val loggedInResponse: LiveData<MyResult<Boolean>> = _response

    fun checkIfSignedIn() {
        checkUserSignedInUseCase.execute(
            stateReducer = { response -> _response.value = response }
        )
    }


//    private val jokes: MutableLiveData<MyResult<List<Joke>>> by lazy {
//        MutableLiveData<MyResult<List<Joke>>>().also {
//            loadJokes()
//        }
//    }

//    private val _loading = MutableLiveData<Boolean>(false)
//    val loading: LiveData<Boolean>
//        get() = _loading
//
//    fun getJokes(): LiveData<MyResult<List<Joke>>> {
//        return jokes
//    }
//
//    private fun loadJokes() = viewModelScope.launch {
//        //1. enable loading
//        _loading.value = true
//
//        //2. load jokes
//        val response = repository.getJokes()
//
//        //3. notify jokes are loaded and disable loading
//        withContext(Dispatchers.Main) {
//            jokes.value = response
//            _loading.value = false
//        }
//    }
}
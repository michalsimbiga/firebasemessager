package com.application.presentation.main

import androidx.lifecycle.*
import com.application.di.module.ViewModelAssistedFactory
import com.application.presentation.base.BaseViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MainViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle
) : BaseViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

//    private val _response = MutableLiveData<MyResult<Boolean>>()
//    val loggedInResponse: LiveData<MyResult<Boolean>> = _response


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
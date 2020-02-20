package com.application.ui

import androidx.lifecycle.*
import com.application.di.module.ViewModelAssistedFactory
import com.application.model.Joke
import com.application.net.Response
import com.application.repository.MyRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @AssistedInject constructor(
    private val repository: MyRepository,
    @Assisted private val stateHandle: SavedStateHandle): ViewModel() {

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>

    private val jokes: MutableLiveData<Response<List<Joke>>> by lazy {
        MutableLiveData<Response<List<Joke>>>().also {
            loadJokes()
        }
    }

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    fun getJokes(): LiveData<Response<List<Joke>>> {
        return jokes
    }

    private fun loadJokes() = viewModelScope.launch {
        //1. enable loading
        _loading.value = true

        //2. load jokes
        val response = repository.getJokes()

        //3. notify jokes are loaded and disable loading
        withContext(Dispatchers.Main) {
            jokes.value = response
            _loading.value = false
        }
    }
}
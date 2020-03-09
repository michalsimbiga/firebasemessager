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
}
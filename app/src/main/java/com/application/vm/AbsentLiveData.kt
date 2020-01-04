package com.application.vm

import androidx.lifecycle.LiveData

/*
 * This class is taken from
 * https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/util/AbsentLiveData.kt
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
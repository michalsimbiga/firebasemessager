package com.application.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.domain.common.useCase.UseCase
import com.application.domain.errorHandling.MyError
import com.application.domain.net.MyResult
import com.application.domain.net.failure
import com.application.domain.net.fold
import com.application.domain.net.success
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.*
import timber.log.Timber

typealias responseReceiver<T> = (MyResult<T>) -> Unit

abstract class BaseViewModel : ViewModel() {

    protected val viewModelJob = SupervisorJob()
    protected val backgroundScope = CoroutineScope(viewModelJob + Dispatchers.Default)

    private val loadingTrue = MyResult.Loading(isLoading = true)
    private val loadingFalse = MyResult.Loading(isLoading = false)

//    fun <T : Any> execute(
//        receiver: responseReceiver<T>,
//        request: suspend (() -> (MyResult<T>))
//    ) = viewModelScope.launch(Dispatchers.Main) {
//        receiver(loadingTrue)
//        val result = withContext(Dispatchers.IO) { request() }
//        handleResult(result, receiver)
//    }

    fun <T : Any, I> UseCase<T, I>.execute(
        params: I,
        stateReducer: (MyResult<T>) -> Unit
    ) {
        this.invoke(viewModelJob, params) { result -> stateReducer(result) }
    }

    fun <T : Any, V: Any> NoParametersUseCase<T>.execute(
        mapper: ((T) -> V),
        stateReducer: (MyResult<V>) -> Unit
    ) {
        this.invoke(viewModelJob) { result -> handleResult(result, mapper, stateReducer) }
    }

    fun <T : Any> NoParametersUseCase<T>.execute(
        stateReducer: (MyResult<T>) -> Unit
    ) {
        this.invoke(viewModelJob) { result -> handleResult(result, stateReducer) }
    }

    private fun <T : Any, V : Any> handleResult(
        result: MyResult<T>,
        mapper: ((T) -> V),
        receiver: responseReceiver<V>
    ) =
        result.fold(
            foldError = { handleError(it, receiver) },
            foldSuccess = {
                val success = MyResult.Success(mapper(it))
                receiver(success)
            }
        )

    private fun <T : Any> handleResult(
        result: MyResult<T>,
        receiver: responseReceiver<T>
    ) =
        result.fold(
            foldError = { handleError(it, receiver) },
            foldSuccess = {
                val success = MyResult.Success(it)
                receiver(success)
            }
        )

    private fun <T : Any> handleError(error: Exception, receiver: responseReceiver<T>) {
        Timber.e("Error ${error.message} ${error.localizedMessage}")
        receiver(failure(error))
    }

//    fun <T : Any> executeFirebase(
//        receiver: responseReceiver<T>,
//        request: suspend (() -> (Task<T>))
//    ) = viewModelScope.launch(Dispatchers.Main) {
//        receiver(loadingTrue)
//        withContext(Dispatchers.IO) {
//            request()
//                .addOnCompleteListener {
//                    receiver(loadingFalse)
//                    if (it.isSuccessful) receiver(success(it.result as T))
//                    else receiver(failure(it.exception!!))
//                }
//        }
//    }

//    fun <T : Any?> executeFirebase(
//        onSuccessReceiver: (T) -> Unit,
//        onFailureReceiver: (Exception) -> Unit,
//        request: suspend (() -> (Task<T>))
//    ) = viewModelScope.launch(Dispatchers.Main) {
//        withContext(Dispatchers.IO) {
//            request()
//                .addOnCompleteListener {
//                    if (it.isSuccessful) onSuccessReceiver(it.result as T)
//                    else onFailureReceiver(it.exception!!)
//                }
//        }
//    }
}
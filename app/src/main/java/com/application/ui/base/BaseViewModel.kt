package com.application.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.net.MyResult
import com.application.net.failure
import com.application.net.success
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias responseReceiver<T> = (MyResult<T>) -> Unit

abstract class BaseViewModel : ViewModel() {

    private val loadingTrue = MyResult.Loading(isLoading = true)
    private val loadingFalse = MyResult.Loading(isLoading = false)

    fun <T : Any> execute(
        receiver: responseReceiver<T>,
        request: suspend (() -> (MyResult<T>))
    ) = viewModelScope.launch(Dispatchers.Main) {
        receiver(loadingTrue)
        val result = withContext(Dispatchers.IO) { request() }
        handleResult(result, receiver)
    }

//    fun <T : Any> executeFirebase(
//        receiver: responseReceiver<T>,
//        request: suspend (() -> (Task<T>))
//    ) = viewModelScope.launch(Dispatchers.Main) {
//        receiver(loadingTrue)
//        withContext(Dispatchers.IO) {
//            request()
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        receiver(loadingFalse)
//                        receiver(success(it.result as T))
//                    }
//                }
//                .addOnFailureListener {
//                    receiver(loadingFalse)
//                    receiver(failure(it))
//                }
//        }
//    }

    private fun <T : Any> handleResult(
        result: MyResult<T>,
        receiver: responseReceiver<T>
    ) = with(receiver) {
        invoke(loadingFalse)
        invoke(result)
    }


}
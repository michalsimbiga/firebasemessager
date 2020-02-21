package com.application.vm

import androidx.lifecycle.Observer
import com.application.net.MyResult

//class ResponseObserver<T: Any>(
//    private val onSuccess: (T) -> Unit,
//    private val onError: (String) -> Unit,
//    private val onException: (Exception) -> Unit)
//    : Observer<MyResult<T>> {
//
//    override fun onChanged(response: MyResult<T>?) {
//        when (response) {
//            is com.application.net.Response.Result.Success -> onSuccess(response.data)
//            is com.application.net.Response.Result.Error -> onError(response.toString())
//            is com.application.net.Response.Result.Exception -> onException(response.exception)
//        }
//    }
//}
//
//class ResponseEventObserver<T: Any> (
//    private val onSuccess: (T) -> Unit,
//    private val onError: (String) -> Unit,
//    private val onException: (Exception) -> Unit)
//    : Observer<Event<MyResult<T>>> {
//
//    override fun onChanged(response: Event<MyResult<T>>) {
//        when (val r = response.peek()) {
//            is com.application.net.Response.Result.Success -> onSuccess(r.data)
//            is com.application.net.Response.Result.Error -> {
//                response.pop()
//                onError(r.toString())
//            }
//            is com.application.net.Response.Result.Exception -> {
//                response.pop()
//                onException(r.exception)
//            }
//        }
//    }
//}
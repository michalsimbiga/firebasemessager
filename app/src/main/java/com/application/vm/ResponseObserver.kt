package com.application.vm

import androidx.lifecycle.Observer
import com.application.net.Response
import com.application.vm.Event

class ResponseObserver<T: Any>(
    private val onSuccess: (T) -> Unit,
    private val onError: (String) -> Unit,
    private val onException: (Exception) -> Unit)
    : Observer<Response<T>> {

    override fun onChanged(response: Response<T>?) {
        when (response) {
            is Response.Success -> onSuccess(response.data)
            is Response.Error -> onError(response.toString())
            is Response.Exception -> onException(response.exception)
        }
    }
}

class ResponseEventObserver<T: Any> (
    private val onSuccess: (T) -> Unit,
    private val onError: (String) -> Unit,
    private val onException: (Exception) -> Unit)
    : Observer<Event<Response<T>>> {

    override fun onChanged(response: Event<Response<T>>) {
        when (val r = response.peek()) {
            is Response.Success -> onSuccess(r.data)
            is Response.Error -> {
                response.pop()
                onError(r.toString())
            }
            is Response.Exception -> {
                response.pop()
                onException(r.exception)
            }
        }
    }
}
package com.application.net

import com.application.extensions.empty

sealed class MyResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : MyResult<T>()
    data class Failure(val exception: Exception, val message: String) : MyResult<Nothing>()
    data class Loading(val isLoading: Boolean) : MyResult<Nothing>()
}

fun <T : Any> success(data: T) = MyResult.Success(data)
fun failure(exception: java.lang.Exception, message: String = String.empty) =
    MyResult.Failure(exception, message)
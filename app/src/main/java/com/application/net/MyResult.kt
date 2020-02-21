package com.application.net

sealed class MyResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : MyResult<T>()
    data class Failure(val exception: Exception) : MyResult<Nothing>()
    data class Loading(val isLoading: Boolean) : MyResult<Nothing>()
}

fun <T: Any> success(data: T) = MyResult.Success(data)
fun failure(exception: java.lang.Exception) = MyResult.Failure(exception)
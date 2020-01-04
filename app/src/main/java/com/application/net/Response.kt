package com.application.net

sealed class Response<out T : Any> {

    data class Success<out T : Any>(val data: T) : Response<T>()
    data class Error(val code: Int, val message: String) : Response<Nothing>() {
        override fun toString() = "$code: $message"
    }
    data class Exception(val exception: kotlin.Exception) : Response<Nothing>()
}
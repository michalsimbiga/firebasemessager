package com.application.domain.errorHandling

abstract class MyException : Exception() {
    abstract val originalException: Throwable?
}

sealed class MyError : MyException() {
    data class UserNotSignedIn(override val originalException: Throwable? = null) : MyError()
}
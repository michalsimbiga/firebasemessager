package com.application.repository

import retrofit2.Response

open class BaseRepository {

    suspend fun <T : Any> call(call: suspend () -> Response<T>): com.application.net.Response<T> {
        try {
            val response = call.invoke()

            //HTTP OK (200)
            return if (response.isSuccessful) {
                com.application.net.Response.Success(response.body() as T)

                //HTTP Error
            } else {
                com.application.net.Response.Error(
                    response.code(),
                    response.errorBody()?.string() ?: ""
                )
            }

            //Network Exception
        } catch (e: Exception) {
            return com.application.net.Response.Exception(e)
        }
    }
}
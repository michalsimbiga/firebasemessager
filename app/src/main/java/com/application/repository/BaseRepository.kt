package com.application.repository

import com.application.net.MyResult
import retrofit2.Response

open class BaseRepository {

//    suspend fun <T : Any> call(call: suspend () -> Response<T>): MyResult<T> {
//        try {
//            val response = call.invoke()
//
//            //HTTP OK (200)
//            return if (response.isSuccessful) {
//                com.application.net.MyResult.Success(response.body() as T)
//
//                //HTTP Error
//            } else {
//                com.application.net.MyResult.Error(
//                    response.code(),
//                    response.errorBody()?.string() ?: ""
//                )
//            }
//
//            //Network Exception
//        } catch (e: Exception) {
//            return com.application.net.MyResult.Exception(e)
//        }
//    }
}
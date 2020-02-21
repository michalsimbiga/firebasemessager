package com.application.repository

import com.application.model.Joke
import com.application.net.MyResult
import com.application.net.RestApi

@Suppress("UNCHECKED_CAST")
class MyRepository(private val restApi: RestApi) : BaseRepository() {

//    suspend fun getJokes(): MyResult<List<Joke>> {
//        val result = call { restApi.getJokes() }
//
//        return if (result is com.application.net.Response.Result.Success) {
//            MyResult.Success(result.data.value)
//        } else result as MyResult<List<Joke>>
//    }
}
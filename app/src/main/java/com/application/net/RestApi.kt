package com.application.net

import com.application.model.Joke
import com.application.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {

    @GET("/jokes/random/5")
    suspend fun getJokes(): Response<JokeResponse<List<Joke>>>
}
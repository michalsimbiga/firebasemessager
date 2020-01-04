package com.application.model

import com.google.gson.annotations.SerializedName

data class JokeResponse<T>(

    @SerializedName("type")
    val type: String,

    @SerializedName("value")
    val value: T
)
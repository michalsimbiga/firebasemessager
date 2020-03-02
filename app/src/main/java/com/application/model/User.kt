package com.application.model

import com.application.extensions.empty

data class User(
    val username: String = String.empty,
    val email: String = String.empty,
    val profileImage: String = String.empty
){
}
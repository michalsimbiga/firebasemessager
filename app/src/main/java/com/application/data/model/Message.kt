package com.application.data.model

import com.application.domain.extensions.empty

data class Message(
    val id: String = String.empty,
    val text: String = String.empty,
    val fromId: String = String.empty,
    val toId: String = String.empty,
    val timestamp: Long = 0L
)
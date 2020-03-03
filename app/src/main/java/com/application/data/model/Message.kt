package com.application.data.model

data class Message(
    val id: String,
    val text: String,
    val fromId: String,
    val toId: String,
    val timestamp: Long
)